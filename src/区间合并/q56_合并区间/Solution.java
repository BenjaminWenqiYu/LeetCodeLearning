package 区间合并.q56_合并区间;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2020-11-02
 * Time: 19:49
 * Description:
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 *  
 *
 * 提示：
 *
 * intervals[i][0] <= intervals[i][1]
 *
 */
public class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        // 按照上界排序
        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0]));
        int[] currInterval = intervals[0];
        List<int[]> resArr = new ArrayList<>();
        resArr.add(currInterval);
        for (int[] interval: intervals) {
            int currEnd = currInterval[1];
            int nextBegin = interval[0];
            int nextEnd = interval[1];
            if (currEnd >= nextBegin) {
                currInterval[1] = Math.max(currEnd, nextEnd);
            } else {
                currInterval = interval;
                resArr.add(currInterval);
            }
        }

        return resArr.toArray(new int[resArr.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[2][];
        intervals[0] = new int[] {1, 4};
        intervals[1] = new int[] {4, 5};
        System.out.println(new Solution().merge(intervals));
    }

}
