package 其他.编码问题;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2021-01-08
 * Time: 14:09
 * Description: 解读文件编码异常问题
 */
public class FileReaderTest {
    public static void main(String[] args) throws IOException {
        Files.deleteIfExists(Paths.get("jay.txt"));
        Files.write(Paths.get("jay.txt"), "你好，捡田螺的小男孩".getBytes(Charset.forName("GBK")));
        System.out.println("系统默认编码：" + Charset.defaultCharset());

        char[] chars = new char[10];
        String content = "";
        //
        // FileReader 是以当前机器的默认字符集 来读取文件的，
        // 如果希望指定字符集的话，需要直接使用InputStreamReader和FileInputStream
        //
        /*try (FileReader fileReader = new FileReader("jay.txt")) {
            int count;
            while ((count = fileReader.read(chars)) != -1) {
                content += new String(chars, 0, count);
            }
        }*/

        // 正解如下
        try (InputStreamReader inputStreamReader =
                     new InputStreamReader(
                             new FileInputStream("jay.txt"), Charset.forName("GBK"))) {
            int count;
            while ((count = inputStreamReader.read(chars)) != -1) {
                content += new String(chars, 0, count);
            }
        }
        System.out.println(content);
    }
}
