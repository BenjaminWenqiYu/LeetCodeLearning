package 栈相关.q224_基本计算器;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: yuwenqi
 * Date: 2020-10-29
 * Time: 11:56
 * Description:
 *
 * 1 正序迭代字符串。
 * 2 操作数可以由多个字符组成，字符串 "123" 表示数字 123，它可以被构造为：123 >> 120 + 3 >> 100 + 20 + 3。
 *      如果我们读取的字符是一个数字，则我们要将先前形成的操作数乘以 10 并于读取的数字相加，形成操作数。
 * 3 每当我们遇到 + 或 - 运算符时，我们首先将表达式求值到左边，然后将正负符号保存到下一次求值。
 * 4 如果字符是左括号 (，我们将迄今为止计算的结果和符号添加到栈上，然后重新开始进行计算，就像计算一个新的表达式一样。
 * 5 如果字符是右括号 )，则首先计算左侧的表达式。则产生的结果就是刚刚结束的子表达式的结果。如果栈顶部有符号，则将此结果与符号相乘。
 *
 */
public class Solution1 {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int operand = 0;
        int result = 0; // For the on-going result
        int sign = 1; // 1 means positive, -1 means negative

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                // Forming operand, since it could be more than one digit
                operand = 10 * operand + (ch - '0');
            } else if (ch == '+') {
                // Evaluate the expression to the left,
                // with result, sign, operand
                result += sign * operand;
                // Save the recently encountered '+' sign
                sign = 1;
                // Reset operand
                operand = 0;
            } else if (ch == '-') {
                result += sign * operand;
                sign = -1;
                operand = 0;
            } else if (ch == '(') {
                // Push the result and sign on to the stack, for later
                // We push the result first, then sign
                stack.push(result);
                stack.push(sign);
                // Reset operand and result, as if new evaluation begins for the new sub-expression
                sign = 1;
                result = 0;
            } else if (ch == ')') {
                // Evaluate the expression to the left
                // with result, sign and operand
                result += sign * operand;
                // ')' marks end of expression within a set of parenthesis
                // Its result is multiplied with sign on top of stack
                // as stack.pop() is the sign before the parenthesis
                result *= stack.pop();
                // Then add to the next operand on the top.
                // as stack.pop() is the result calculated before this parenthesis
                // (operand on stack) + (sign on stack * (result from parenthesis))
                result += stack.pop();
                // Reset the operand
                operand = 0;
            }

        }
        return result + (sign * operand);
    }

    public static void main(String[] args) {
        String str1 = "1 + 1";
        System.out.println(new Solution1().calculate(str1));
        String str2 = " 2-1 + 2 ";
        System.out.println(new Solution1().calculate(str2));
        String str3 = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(new Solution1().calculate(str3));
    }
}
