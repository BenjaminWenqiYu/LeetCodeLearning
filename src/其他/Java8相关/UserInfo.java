package 其他.Java8相关;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2021-03-11
 * Time: 14:00
 * Description: 实例类
 */
public class UserInfo {
    private String username;
    private String password;
    private Integer age;
    private String gender;

    public UserInfo() {
    }

    public UserInfo(String username, String password, Integer age, String gender) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("UserInfo(username=")
                .append(this.username)
                .append(", password=")
                .append(this.password)
                .append(", age=")
                .append(this.age)
                .append(", gender=")
                .append(this.gender)
                .append(")");
        return res.toString();
    }
}
