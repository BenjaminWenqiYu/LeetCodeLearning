package 栈相关.q20_有效的括号;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: yuwenqi
 * Date: 2020-10-28
 * Time: 09:34
 * Description:
 */
public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[' || c == '{' || c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    if (c == ')') {
                        if (stack.pop() != '(') {
                            return false;
                        }
                    } else if (c == ']') {
                        if (stack.pop() != '[') {
                            return false;
                        }
                    } else if (c == '}') {
                        if (stack.pop() != '{') {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 使用Hash表
     *
     * 时间复杂度：O(n)，其中 n 是字符串 s 的长度。
     *
     * 空间复杂度：O(n+∣Σ∣)，其中 Σ 表示字符集，
     * 本题中字符串只包含 6 种括号，∣Σ∣=6。栈中的字符数量为 O(n)，
     * 而哈希映射使用的空间为 O(∣Σ∣)，相加即可得到总空间复杂度。
     *
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (pairs.containsKey(c)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(c)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isValid("([]?"));
    }
}
