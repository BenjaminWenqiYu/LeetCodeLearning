package 分治法.q34_在排序数组中查找元素的第一个和最后一个位置;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2020-11-02
 * Time: 10:26
 * Description:
 *
 *  给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 */
public class Solution {
    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int low = 0;
        int high = nums.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }


    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};
        int leftIdx = extremeInsertionIndex(nums, target, true);
        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
//            System.out.println("[" + targetRange[0] + "," + targetRange[1] + "]");
            return targetRange;
        }
        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false) - 1;
//        System.out.println("[" + targetRange[0] + "," + targetRange[1] + "]");
        return targetRange;
    }

    public static void main(String[] args) {
       new Solution().searchRange(new int[] {5,7,7,8,8,10}, 8);
    }
}
