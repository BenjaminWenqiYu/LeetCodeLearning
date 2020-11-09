package 栈相关.q232_用栈实现队列;


import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2020-10-30
 * Time: 14:43
 * Description:
 */
public class MyQueue2 {
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();
    private Integer front;

    public void push(int x) {
        if (s1.empty()) {
            front = x;
        }
        s1.push(x);
    }

    public int pop() {
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    public int peek() {
        if (!s2.isEmpty()) {
            return s2.peek();
        }
        return front;
    }

    public static void main(String[] args) {
        MyQueue2 myQueue = new MyQueue2();
        myQueue.push(1);
        myQueue.push(2);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.empty());
    }
}
