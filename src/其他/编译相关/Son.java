package 其他.编译相关;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2021-03-04
 * Time: 08:34
 * Description:
 */
class Father {
    static {
        System.out.println("Static Father!!! 静态代码块，编译时，加载一次");
    }
    {
        System.out.println("Non-static Father!! 实例化时加载，初始化几次就加载几次");
    }
    public Father() {
        System.out.println("Constructor Father");
    }
}
public class Son extends Father {
    static {
        System.out.println("Static Son!!! 静态代码块，编译时，加载一次");
    }
    {
        System.out.println("Non-static Son!! 实例化时加载，初始化几次就加载几次");
    }
    public Son() {
        System.out.println("Constructor Son");
    }

    public static void main(String[] args) {
        System.out.println("First Son");
        new Son();
        System.out.println("Second Son");
        new Son();
    }
}
