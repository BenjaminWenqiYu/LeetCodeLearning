package hash相关.q1_两数之和;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2020-10-15
 * Time: 16:32
 * Description:
 */
public class Solution {
    /**
     * 暴力法
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * 一遍hash法
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 3, 5, 7, 9};
        Arrays.stream(new Solution().twoSum2(nums, 4)).forEach(System.out::println);
    }
}
