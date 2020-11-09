package 动态规划.q746_使用最小花费爬楼梯;


/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2020-10-28
 * Time: 14:30
 * Description:
 * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 *
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 *
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 *
 * 示例 1:
 *
 * 输入: cost = [10, 15, 20]
 * 输出: 15
 * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
 *  示例 2:
 *
 * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出: 6
 * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
 * 注意：
 *
 * cost 的长度将会在 [2, 1000]。
 * 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]
 *
 */
public class Solution {
    /**
     * dp[i] = min(dp[i - 2], dp[i - 1]) + cost[i]
     * 初始条件：
     * 最后一步踏上第0级台阶，最小花费dp[0] = cost[0]
     * 最后一步踏上第一级
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 2], dp[i - 1]) + cost[i];
        }
        return Math.min(dp[cost.length - 2], dp[cost.length - 1]);
    }

    /**
     * 从最后一级台阶走到第一个台阶
     * f[i] = cost[i] + min(f[i + 1], f[i + 2])
     * @param cost
     * @return
     */
    public int minCostClimbingStairs2(int[] cost) {
        int f1 = 0, f2 = 0;
        for (int i = cost.length - 1; i >= 0; i--) {
            int f0 = cost[i] + Math.min(f1, f2);
            f2 = f1;
            f1 = f0;
        }
        return Math.min(f1, f2);
    }


    public static void main(String[] args) {
        int[] cost = {10, 15, 20};
        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(new Solution().minCostClimbingStairs(cost));
        System.out.println(new Solution().minCostClimbingStairs(cost2));
        System.out.println(new Solution().minCostClimbingStairs2(cost));
        System.out.println(new Solution().minCostClimbingStairs2(cost2));
    }
}
