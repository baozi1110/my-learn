package cn.qp.myleetcode.code;

import java.util.HashMap;
import java.util.Stack;

/**
 * 020
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 */
public class ValidParentheses {
    // 用于处理映射的哈希表
    private HashMap<Character, Character> mappings;

    // 使用映射初始化哈希映射。这简单地使代码更容易阅读。
    ValidParentheses() {
        this.mappings = new HashMap<>();
        this.mappings.put(')', '(');
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
    }

    public boolean isValid(String s) {
        // 初始化要在算法中使用的堆栈。
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //如果当前字符是结束括号。
            if (this.mappings.containsKey(c)) {
                // 获取堆栈的顶部元素。如果堆栈为空，请设置虚拟值“＃”
                char topElement = stack.empty() ? '#' : stack.pop();
                // 如果此括号的映射与堆栈的top元素不匹配，则返回false。
                if (topElement != this.mappings.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        // 如果堆栈仍包含元素，那么它是一个无效的表达式。
        return stack.isEmpty();
    }
}