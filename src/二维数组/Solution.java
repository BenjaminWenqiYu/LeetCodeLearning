package 二维数组;

/**
 * Created with IntelliJ IDEA.
 * User: yuwenqi
 * Date: 2020-10-27
 * Time: 18:10
 * Description:
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 * 0 <= n <= 1000
 *
 * 0 <= m <= 1000
 */
public class Solution {
    /**
     * 暴力法
     * 时间复杂度 O(NM)
     * 空间复杂度 O(1)
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 线性查找
     *
     * 从二维数组的右上角开始查找，如果当前元素等于目标值，则返回true，
     * 如果当前元素大于目标值，则移动到左边一列；
     * 如果当前元素小于目标值，则移动到下边一行
     *
     * 时间复杂度 O(N + M)
     * 空间复杂度 O(1)
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int row = 0, column = columns - 1, num = 0;
        while (row < rows && column >= 0) {
            num = matrix[row][column];
            if (num == target) {
                return true;
            } else if (num > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[5][5];
        matrix[0] = new int[] {1, 4, 7, 11, 15};
        matrix[1] = new int[] {2, 5, 8, 12, 19};
        matrix[2] = new int[] {3, 6, 9, 16, 22};
        matrix[3] = new int[] {10, 13, 14, 17, 24};
        matrix[4] = new int[] {18, 21, 23, 26, 30};
        System.out.println(new Solution().findNumberIn2DArray2(matrix, 5));
        System.out.println(new Solution().findNumberIn2DArray2(matrix, 20));
    }

}
