package 回溯法.q22_括号生成;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2020-11-09
 * Time: 10:35
 * Description:
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *  
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *
 */
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        dfs("", 0, 0, n, res);
        return res;
    }

    /**
     *
     * @param curStr    当前递归得到的结果
     * @param left      左括号已经用了几个
     * @param right     右括号已经用了几个
     * @param n         左括号、右括号一共得到几个
     * @param res       结果集
     */
    private void dfs(String curStr, int left, int right, int n, List<String> res) {
        if (left == n && right == n) {
            res.add(curStr);
            return;
        }
        // 剪枝
        if (left < right) {
            return;
        }
        if (left < n) {
            dfs(curStr + "(", left + 1, right, n, res);
        }
        if (right < n) {
            dfs(curStr + ")", left, right + 1, n, res);
        }
    }

    public static void main(String[] args) {
        new Solution().generateParenthesis(3).forEach(System.out::println);
    }
}
