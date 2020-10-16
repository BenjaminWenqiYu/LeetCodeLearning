package hash相关.q387_字符串中的第一个唯一字符;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: yuwenqi
 * Date: 2020-10-16
 * Time: 11:06
 * Description: 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1
 */
public class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> count = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            Character c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String s1 = "leetcode";
        String s2 = "loveleetcode";
        System.out.println(new Solution().firstUniqChar(s1));
        System.out.println(new Solution().firstUniqChar(s2));
    }
}
