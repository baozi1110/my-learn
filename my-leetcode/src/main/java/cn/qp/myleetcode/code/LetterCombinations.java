package cn.qp.myleetcode.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 017
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class LetterCombinations {
    static Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    public static void main(String[] args) {
        List<String> list = letterCombinations("234");
        System.out.println(list);
    }

    static List<String> output = new ArrayList<>();

    public static List<String> letterCombinations(String digits) {
        if (digits.length() != 0) {
            backtrack("", digits);
        }
        return output;
    }

    /**
     * 回溯函数
     * 如果没有更多的数字需要被输入，那意味着当前的组合已经产生好了。
     * 如果还有数字需要被输入：
     * 遍历下一个数字所对应的所有映射的字母。
     * 将当前的字母添加到组合最后，也就是 combination = combination + letter 。
     *
     * @param combination:目前已经产生的组合
     * @param next_digits:接下来准备要输入的数字
     */
    public static void backtrack(String combination, String next_digits) {
        //如果没有更多的数字需要被输入
        if (next_digits.length() == 0) {
            //当前组合完成，将组合添加到返回值中
            output.add(combination);
        }
        //如果还有数字需要被输入
        else {
            //获取输入的第一个数字，并且从map中获取对应的字符串
            String digit = next_digits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                //把当前字母添加到组合中
                backtrack(combination + letter, next_digits.substring(1));
            }
        }
    }

    /**
     * 通过遍历输入的数字，不断在原有结果上扩展
     *  第一遍获取第一个数字对应的单个字符
     *  第二遍把第二个数字对应的单个字符与第一遍的字符一一组合 3*3
     *  ...
     */
    public List letterCombinations1(String digits) {
        List<String> ans = new ArrayList<String>();
        if (digits == null || digits.isEmpty()) {
            return ans;
        }
        //将对应表转成二维数组
        char[][] map = new char[8][];
        map[0] = "abc".toCharArray();
        map[1] = "def".toCharArray();
        map[2] = "ghi".toCharArray();
        map[3] = "jkl".toCharArray();
        map[4] = "mno".toCharArray();
        map[5] = "pqrs".toCharArray();
        map[6] = "tuv".toCharArray();
        map[7] = "wxyz".toCharArray();

        char[] input = digits.toCharArray();
        ans.add("");
        //遍历输入的数字 2、3
        for (char c : input) {
            ans = expand(ans, map[c - '2']);
        }
        return ans;
    }

    /**
     * @param l:目前已经产生的组合
     * @param arr:数字对应的字母串
     * @return 已有组合与下一个数字对应的字符数组组合
     */
    private List<String> expand(List<String> l, char[] arr) {
        List<String> next = new ArrayList<String>();
        for (String s : l) {
            for (char c : arr) {
                next.add(s+c);
            }
        }
        return next;
    }
}
