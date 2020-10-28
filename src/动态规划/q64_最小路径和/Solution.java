package 动态规划.q64_最小路径和;

/**
 * Created with IntelliJ IDEA.
 * User: yuwenqi
 * Date: 2020-10-28
 * Time: 10:46
 * Description:
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class Solution {
    /**
     * 时间复杂度 O(MN) 其中 m 和 n 分别是网格的行数和列数。需要对整个网格遍历一次，计算 dp 的每个元素的值
     * 空间复杂度 O(mn)，其中 m 和 n 分别是网格的行数和列数。创建一个二维数组 dp，和网格大小相同
     *
     * 创建二维数组 dp，与原始网格的大小相同，dp[i][j] 表示从左上角出发到 (i,j) 位置的最小路径和。
     * 显然，dp[0][0]=grid[0][0]。对于 dp 中的其余元素，通过以下状态转移方程计算元素值。
     *
     * 当 i>0 且 j=0 时，dp[i][0]=dp[i−1][0]+grid[i][0]。
     *
     * 当 i=0 且 j>0 时，dp[0][j]=dp[0][j−1]+grid[0][j]。
     *
     * 当 i>0 且 j>0 时，dp[i][j]=min(dp[i−1][j],dp[i][j−1])+grid[i][j]
     *
     * 最后得到 dp[m−1][n−1] 的值即为从网格左上角到网格右下角的最小路径和。
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rows - 1][columns - 1];
    }

    public static void main(String[] args) {
        int[][] grid = new int[3][3];
        grid[0] = new int[] {1, 3, 1};
        grid[1] = new int[] {1, 5, 1};
        grid[2] = new int[] {4, 2, 1};
        System.out.println(new Solution().minPathSum(grid));
    }
}
