package 回溯法.q10_正则表达式匹配;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2020-11-04
 * Time: 14:34
 * Description:
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 *  
 * 示例 1：
 *
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 *
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4：
 *
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5：
 *
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 *  
 *
 * 提示：
 *
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 *
 */
public class Solution {
    /**
     * 对于*字符，可以直接忽略模式串中这一部分，或者删除匹配串的第一个字符，
     * 前提是它能够匹配模式串当前位置字符，即 pattern[0]。
     * 如果两种操作中有任何一种使得剩下的字符串能匹配，那么初始时，匹配串和模式串就可以被匹配。
     * @param text
     * @param pattern
     * @return
     */
    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        boolean firstMatch = !text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.');
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return (isMatch(text, pattern.substring(2)) || (firstMatch && isMatch(text.substring(1), pattern)));
        } else {
            return firstMatch && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aaa", "a*a"));
    }
}
