package 其他.面试试题;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2021-03-10
 * Time: 08:39
 * Description: 试把一个正整数n拆分为若干个连续正整数之和。
 *  例如，n=15,可拆分为：
 * 15=1+2+3+4+5,
 * 15=4+5+6,
 * 15=7+8。
 * 15=15
 *
 *      a、每组数总会有一个起始项i，i可以是1，也可以是其它数；
 *      b、每组数总会有一个或一个以上的累加项，记为i+k；k为1,2,3...；
 *      c、（重要）每组数必满足公式： sum = i + (i+1) + ... + (i+k) = (k+1) * (2*i + k) / 2。
 */
public class Solution1 {
    /**
     * 暴力法
     * @param num
     */
    public void sum(int num) {
        int sum = 0;
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= num / 2; i++) {
            for (int k = 1; ; k++) {
                sum = (k + 1) * (2 * i + k) / 2;
                if (sum > num) {
                    break;
                }
                if (num == sum) {
                    String tmp = num + "=";
                    for (int j = i; j <= i + k; j++) {
                        if (j == i + k) {
                            tmp = tmp + j;
                        } else {
                            tmp = tmp + j + "+";
                        }
                    }
                    res.add(tmp);
                }
            }
        }
        res.add(num + "=" + num);
        for (int i = res.size() - 1; i >=0; i--) {
            System.out.println(res.get(i));
        }
    }

    /**
     * 由公式 k * (2 * i + k - 1) = 2 * num 知道 2 * num 能被k整除
     * @param num
     */
    public void sum2(int num) {
        int i = 0;
        List<String> res = new ArrayList<>();
        res.add(num + "=" + num);
        for (int k = 1; k <= num / 2; k++) {
            if (2 * num % k == 0) {
                i = (2 * num / k -k + 1) / 2;
                if (i > 0) {
                    if (k > 1) {
                        String tmp = num + "=";
                        for (int j = i; j <= i + k -1; j++) {
                            if (j == i + k - 1) {
                                tmp = tmp + j;
                            } else {
                                tmp = tmp + j + "+";
                            }
                        }
                        res.add(tmp);
                    }
                }
            }
        }
        for (i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

    public static void main(String[] args) {
        new Solution1().sum2(15);
    }
}
