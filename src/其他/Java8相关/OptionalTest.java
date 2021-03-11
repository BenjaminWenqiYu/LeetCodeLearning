package 其他.Java8相关;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2021-03-11
 * Time: 13:57
 * Description: Optional 使用示例
 */
public class OptionalTest {

    public static void testBuildOptional() {
        // 构建一个value为null的Optional对象
        Optional<UserInfo> userInfoEmptyOpt = Optional.empty();

        // 构建一个value不可以为null的optional对象，如果of()的入参为null会报空指针异常
        Optional<UserInfo> userInfoOpt = Optional
                .of(new UserInfo("阿飞", "123456", 15, "man"));

        // 构建一个value可以为null的optional对象
        Optional<UserInfo> userInfoNullOpt = Optional.ofNullable(null);

        System.out.println(userInfoEmptyOpt);
        System.out.println(userInfoOpt);
        System.out.println(userInfoNullOpt);
    }

    public static void testGetOptionalValue() throws Exception {
        Optional<UserInfo> userInfoEmptyOpt = Optional.empty();
        Optional<UserInfo> userInfoOpt = Optional
                .of(new UserInfo("阿飞", "123456", 15, "man"));

        // 直接获取，注意如果value==null，会报NoSuchElementException异常
        UserInfo userInfo1 = userInfoOpt.get();
        // orElse可以传入一个UserInfo类型的对象作为默认值;
        // 当value!=null时，返回value值；当value==null时，返回默认值作为代替；
        UserInfo userInfo2 = userInfoEmptyOpt
                .orElse(new UserInfo("阿飞", "123456", 15, "man"));
        // orElseGet和orElse不同的是orElseGet可以传入一段lambda表达式；
        // 当value!=null时，返回value值；当value==null时，使用该lambda返回的对象作为默认值；
        UserInfo userInfo3 = userInfoEmptyOpt
                .orElseGet(() -> new UserInfo("阿飞", "123456", 15, "man"));
        // orElseThrow可以传入一段lambda表达式，lambda返回一个Exception；
        // 当value!=null时，返回value值；当value==null时，抛出该异常；
        UserInfo userInfo4 = userInfoOpt.orElseThrow(NullPointerException::new);


        System.out.println(userInfo1);
        System.out.println(userInfo2);
        System.out.println(userInfo3);
        System.out.println(userInfo4);
    }

    public static void testMapOptionalValue() {
        Optional<UserInfo> userInfoOpt = Optional
                .of(new UserInfo("阿飞","123456", 15, "man"));
        // 原来value的类型是UserInfo，经过map转换为Optional<String>
        Optional<String> username = userInfoOpt.map(UserInfo::getUsername);

        // 当map的入参也是一个Optional时，经过map转化后会形成Optional<Optional<String>>这种嵌套结构；
        // 但flatMap可以把这种嵌套结构打平；
        Optional<Optional<String>> unFlatMap = userInfoOpt.map(user -> Optional.of(user.getUsername()));
        Optional<String> flatMap = userInfoOpt.flatMap(user -> Optional.of(user.getUsername()));

        System.out.println(username);
        System.out.println(unFlatMap);
        System.out.println(flatMap);
    }

    public static void testJudgeOptionalValue() {
        Optional<UserInfo> userInfoEmptyOpt = Optional.empty();
        Optional<UserInfo> userInfoOpt = Optional
                .of(new UserInfo("阿飞", "123456", 15, "man"));

        // filter传入一个lambda，lambda返回值为boolean；true:不做任何改变，false:返回一个空的optional；
        Optional<UserInfo> userInfo = userInfoOpt.filter(user -> "错误的密码".equals(user.getPassword()));
        System.out.println(userInfo);

        // isPresent就是判断value是不是null；我们在调用get之前，一定要先调用isPresent，
        // 因为直接如果value是null，直接调用get会报异常；
        if (userInfoEmptyOpt.isPresent()) {
            UserInfo value = userInfoEmptyOpt.get();
            System.out.println("optional value:" + value);
        } else {
            System.out.println("optional value==null");
        }

        // ifPresent传入一段lambda，当value!=null时，执行里面的逻辑；当value==null时，啥都不干；
        userInfoOpt.ifPresent(value -> System.out.println("optional value:" + value));
    }


    public static void main(String[] args) throws Exception{
        OptionalTest.testBuildOptional();
        OptionalTest.testGetOptionalValue();
        OptionalTest.testMapOptionalValue();
        OptionalTest.testJudgeOptionalValue();
    }
}
