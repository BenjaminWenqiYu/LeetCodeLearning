package 分治法.q33_搜索旋转排序数组;

/**
 * Created with IntelliJ IDEA.
 * User: yuwenqi
 * Date: 2020-11-02
 * Time: 09:55
 * Description:
 *  给你一个升序排列的整数数组 nums ，和一个整数 target 。
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。（例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] ）。
 *
 * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 *  
 * 示例 1：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [1], target = 0
 * 输出：-1
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * nums 肯定会在某个点上旋转
 * -10^4 <= target <= 10^4
 *
 */
public class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;
        if (n == 1) return nums[0] == target ? 0 : -1;
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 说明前半段有序
            if (nums[mid] >= nums[l]) {
                // 说明target在后半段
                if (nums[l] > target || nums[mid] < target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                // 前半段无序，后半段有序，target在前半段
                if (nums[mid] > target || nums[r] < target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int[] nums2 = {4,5,6,7,0,1,2};
        System.out.println(new Solution().search(nums, 0));
        System.out.println(new Solution().search(nums2, 3));
    }
}
