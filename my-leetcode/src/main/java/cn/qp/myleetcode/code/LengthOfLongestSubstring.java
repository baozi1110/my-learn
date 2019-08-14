package cn.qp.myleetcode.code;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LengthOfLongestSubstring {

    // 使用滑动窗口法，时间复杂度：O(2n) = O(n)，最坏的情况是i和j都迭代n次
    public int lengthOfLongestSubstring02(String s) {
        int n = s.length();
        int ans = 0, i = 0, j = 0;
        // 创建窗口
        Set<Character> set = new HashSet<>();
        while (i < n && j < n) {
            //如果集合中不包含该字符，那么把该字符放入集合，右窗口挪动一位
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans,j-i);
            }else {
                //如果集合中包含下一个字符，剔除最左边的字符
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    //时间复杂度：O(n)，因为只有j迭代n次
    public  int lengthOfLongestSubstring03(String s) {
        int n = s.length(), ans = 0;
        //创建map窗口,i为左区间，j为右区间，右边界移动
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            // 如果窗口中包含当前字符，
            if (map.containsKey(s.charAt(j))) {
                //左边界移动到 相同字符的下一个位置和i当前位置中更靠右的位置，这样是为了防止i向左移动
                i = Math.max(map.get(s.charAt(j)), i);
            }
            //比对当前无重复字段长度和储存的长度，选最大值并替换
            //j-i+1是因为此时i,j索引仍处于不重复的位置，j还没有向后移动，取的[i,j]长度
            ans = Math.max(ans, j - i + 1);
            // 将当前字符为key，下一个索引为value放入map中
            // value为j+1是为了当出现重复字符时，i直接跳到上个相同字符的下一个位置，if中取值就不用+1了
            map.put(s.charAt(j), j+1);
        }
        return ans;
    }
}
