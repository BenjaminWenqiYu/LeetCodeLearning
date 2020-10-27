package 栈相关.q155_最小栈;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: yuwenqi
 * Date: 2020-10-27
 * Time: 10:07
 * Description:
 */
public class MinStack2 {
    long min;
    Stack<Long> stack = new Stack<>();

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(0L);
            min = x;
        } else {
            // 这里入栈的是入栈的值和最小值的差值，有可能为负，也有可能为正
            stack.push(x - min);
            if (x < min) {
                min = x;
            }
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;
        long pop = stack.pop();
        // 因为入栈的是差值，当出栈的为负数的时候，说明栈中最小值已经出栈了
        // 这里要重新更新最小值
        if (pop < 0) {
            min -= pop;
        }
    }

    public int top() {
        long top = stack.peek();
        if (top > 0) {
            // 栈顶元素如果是正的，说明栈顶元素压栈的时候是比栈中最小值大的，根据
            // top = x - min,可以计算 x = top + min
            return (int) (top + min);
        } else {
            //当栈顶元素是负数的时候，说明栈顶元素压栈的时候是比栈中最小值小的，
            //而压栈完之后他会更新最小值min，所以如果在使用上面公式肯定是不行
            //的。如果栈顶元素压栈的时候比最小值小，他会更新最小值，这个最小值
            //就是我们要压栈的值，所以这里直接返回min就行了。
            return (int) min;
        }
    }

    public int getMin() {
        return (int) min;
    }

}
