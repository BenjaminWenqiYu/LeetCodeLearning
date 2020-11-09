package 栈相关.q155_最小栈;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2020-10-27
 * Time: 08:39
 * Description: 当压栈的值小于栈中最小值时，先把最小值入栈，然后再把需要压栈的值入栈，最后再更新栈中最小值。
 * 如果压栈的值大于栈中最小值的时候，直接压栈
 *
 * 出栈的时候如果出栈的值等于最小值，说明最小值已经出栈了，要更新最小值
 */
public class MinStack {
    int min = Integer.MAX_VALUE;
    private Stack<Integer> stack;

    public MinStack() {
        stack = new Stack<>();
    }

    /**
     * 此种方法如果压栈的值一直递减的话，栈中会压入很多的min
     * @param x
     */
    public void push(int x) {
        // 如果加入的值小于最小值，要更新最小值
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        // 如果最小值出栈了，就更新最小值
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());   // 返回 -3.
        minStack.pop();
        System.out.println(minStack.top());      // 返回 0.
        System.out.println(minStack.getMin());   // 返回 -2.
    }
}
