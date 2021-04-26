package 其他.编译相关;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2021-04-25
 * Time: 13:48
 * Description: Java 动态编译测试
 */
public class DynamicCompileTest {

    private static Map<String, JavaFileObject> fileObjects = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        String code = "public class Man {\n" +
                "\tpublic void hello(){\n" +
                "\t\tSystem.out.println(\"hello world\");\n" +
                "\t}\n" +
                "}";

        /**
         * JavaCompiler -- Java编译器
         * ToolProvider类似是一个工具箱,提供JavaCompiler类的实例并返回
         * 然后该实例可以获取JavaCompiler.CompilationTask实例
         * 然后由JavaCompiler.CompilationTask实例来执行对应的编译任务
         *
         * -- 其实这个执行过程是一个并发的过程
         */
        // 1、准备编译器对象
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 2、诊断信息的收集
        DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();
        // 可以通过Diagnostic实例获取编译过程中出错的行号、位置以及错误原因等信息
        collector.getDiagnostics().forEach(item -> System.out.println(item.toString()));
        // 3、源代码文件对象的构建

        // 4、文件管理器对象的构建
        JavaFileManager javaFileManager = new MyJavaFileManager(compiler.getStandardFileManager(collector, null, null));

        // 5、编译选项的选择
        List<String> options = new ArrayList<>();
        options.add("-target"); // 编译目标
        options.add("1.8");

        Pattern CLASS_PATTERN = Pattern.compile("class\\s+([$_a-zA-Z][$_a-zA-Z0-9]*)\\s*");
        Matcher matcher = CLASS_PATTERN.matcher(code);
        String cls;
        if (matcher.find()) {
            cls = matcher.group(1);
        } else {
            throw new IllegalArgumentException("No such class name in " + code);
        }
        // 6、其他问题
        JavaFileObject javaFileObject = new MyJavaFileObject(cls, code);
        Boolean result = compiler.getTask(null, javaFileManager, collector, options, null, Arrays.asList(javaFileObject)).call();

        ClassLoader classloader = new MyClassLoader();

        Class<?> clazz = null;
        try {
            clazz  = classloader.loadClass(cls);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Method method = null;
        try {
            method = clazz.getMethod("hello");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            method.invoke(clazz.newInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
    public static class MyClassLoader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            JavaFileObject fileObject = fileObjects.get(name);
            if (fileObject != null) {
                byte[] bytes = ((MyJavaFileObject)fileObject).getCompiledBytes();
                return defineClass(name, bytes, 0, bytes.length);
            }
            try {
                return ClassLoader.getSystemClassLoader().loadClass(name);
            } catch (Exception e) {
                return super.findClass(name);
            }
        }

    }
    /**
     * 3、源代码文件对象的构建
     */
    public static class MyJavaFileObject extends SimpleJavaFileObject {
        private String source;
        private ByteArrayOutputStream outputStream;
        // 该构造器用来输入源代码
        public MyJavaFileObject(String name, String source) {
            // 1、先初始化父类，由于该URI是通过类名来完成的，必须以.java结尾。
            // 2、如果是一个真实的路径，比如是file:///test/demo/Hello.java则不需要特别加.java
            // 3、这里加的String:///并不是一个真正的URL的schema, 只是为了区分来源
            super(URI.create("String:///" + name + Kind.SOURCE.extension), Kind.SOURCE);
            this.source = source;
        }
        // 该构造器用来输出字节码

        public MyJavaFileObject(String name, Kind kind) {
            super(URI.create("String:///" + name + kind.extension), kind);
            source = null;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            if (source == null) {
                throw new IllegalArgumentException("source == null");
            }
            return source;
        }

        @Override
        public OutputStream openOutputStream() throws IOException {
            outputStream = new ByteArrayOutputStream();
            return outputStream;
        }
        public byte[] getCompiledBytes() {
            return outputStream.toByteArray();
        }

    }

    /**
     * 4、文件管理器对象的构建
     */
    // 这里继承类，不实现接口是为了避免实现过多的方法
    public static class MyJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {
        protected MyJavaFileManager(JavaFileManager fileManager) {
            super(fileManager);
        }

        @Override
        public JavaFileObject getJavaFileForInput(Location location,
                                                  String className,
                                                  JavaFileObject.Kind kind) throws IOException {
            JavaFileObject javaFileObject = fileObjects.get(className);
            if (javaFileObject == null) {
                super.getJavaFileForInput(location, className, kind);
            }
            return javaFileObject;
        }

        @Override
        public JavaFileObject getJavaFileForOutput(Location location,
                                                   String qualifiedClassName,
                                                   JavaFileObject.Kind kind,
                                                   FileObject sibling) throws IOException {
            JavaFileObject javaFileObject = new MyJavaFileObject(qualifiedClassName, kind);
            fileObjects.put(qualifiedClassName, javaFileObject);
            return javaFileObject;
        }
    }
}

