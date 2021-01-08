package 其他.编码问题;

import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2020-12-31
 * Time: 10:00
 * Description:
 */
public class CodeVersion {
    /**
     * 使用 Java 重新编译乱码
     * @param str
     * @throws UnsupportedEncodingException
     */
    public static void recover(String str) throws UnsupportedEncodingException {
        String[] charsets = new String[] {
                "windows-1252", "GB18030", "Big5", "UTF-8"};
        for (int i = 0; i < charsets.length; i++) {
            for (int j = 0; j < charsets.length; j++) {
                if (i != j) {
                    String s = new String(str.getBytes(charsets[i]), charsets[j]);
                    System.out.println("------ 原来编码（A）假设是：" +
                        charsets[j] + ",被错误解读为了（B）：" + charsets[i]);
                    System.out.println(s);
                    System.out.println();
                }
            }
        }
    }
}
