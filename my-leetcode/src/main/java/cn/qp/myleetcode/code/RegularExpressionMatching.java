package cn.qp.myleetcode.code;


/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * 也就是 s需要全部符合p中部分正则
 */
public class RegularExpressionMatching {

    /**
     * 回溯-LeetCode解答，使用递归，切除已经比较过的部分
     */
    boolean isMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        //比较第一位
        boolean first_match = (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));
        //如果p长度大于2，并且第二位是*
        if (p.length() >= 2 && p.charAt(1) == '*') {
            //切除p的前两位，再次与s进行比较 || 首位相同时，切除s首位，与p进行比较
            return (isMatch(s, p.substring(2)) || (first_match && isMatch(s.substring(1), p)));
        } else {
            // 首位相同时，切除第一位。进行比较
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
    }


    /**
     * 使用动态规划
     * 因为递归会出现重叠子问题:
     * 动态规划策略将问题分解为一个或者多个子问题重叠子问题是一个递归解决方案里包含的子问题虽然很多，但不同子问题很少。
     * 少量的子问题被重复解决很多次。
     *
     * 状态定义：
     *  f(x, y)------字符串s中[0, x - 1]范围内的字符串能否匹配字符串p中[0, y - 1]范围内的字符串
     *
     * 动态规划的思想是进行状态传递，先匹配第一个字符是否相同，并把结果传递到下一个比对中，并且需要处理多个特殊情况
     * 1.p(y)=='.'时，当前结果与上次结果一样， f(x, y) = f(x - 1, y - 1)。
     * 2.p(y) == s(x), f(x, y) = f(x - 1, y - 1)
     * 3.p(y)=='*'时:
     *  (1).如果 s(x)==p(y-1) || p(y-1)=='.',即字符串当前位等于正则式上一位或者正则式上一位可以匹配任意字符，
     *  那么此时s(x)与p(y)的正则匹配是正确的，可以忽略掉，要向前进行匹配把前面的状态传递到当前位置
     *      1-1:使用*号进行匹配，f(x,y)=f(x-1,y),如果到x上一位可以匹配上，那加上*也一定匹配
     *      1-2:只使用'*'号前面的那个字符匹配，不使用'*'匹配——f(x, y - 1)
     *      1-3:'*'号和前面的那个字符在匹配的过程当中一个都不使用——f(x, y - 2)
     *  (2).如果s(x) != p(y - 1) && p(y - 1) != '.'
     *      那么不使用*号前面那个字符，向前数两个 f(x, y) = f(x, y - 2)，也就是跳过*和前面那个不匹配的字符
     */
     boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int sn = s.length();
        int pn = p.length();
        //dp[i][j]用来表示s[0,i-1]范围内的字符串与p[0，j-1]是否匹配，可以用来解决相同参数的函数调用
        boolean[][] dp = new boolean[sn + 1][pn + 1];
        //第0行，相当于字符串s为空
        dp[0][0] = true;
        for (int i = 0; i < pn; i++) {
            //如果p当前位置字符为 * ，并且 p[0,i-1]都匹配，那么dp到当前字符都是true
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true;
            }
        }
        for (int i = 0; i < sn; i++) {
            for (int j = 0; j < pn; j++) {
                //
                if (p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                    }
                }

            }
        }
        return dp[sn][pn];
    }

    /**
     * 动态规划，自底向上,从最后一位向前匹配
     *
     */
    public boolean isMatch3(String s, String p) {
        int n1 = s.length();
        int n2 = p.length();
        boolean[][] dp = new boolean[n1 + 1][n2 + 1];
        dp[n1][n2] = true;
        for (int i = n1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                //当前字符串的最后一位是否相同
                boolean firstMatch = i < n1 && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j));
                //如果j不在p的最后一位并且下一位是*号
                if (j + 1 < n2 && p.charAt(j + 1) == '*') {
                    dp[i][j] = firstMatch && dp[i+1][j] || dp[i][j+2];
                }else {
                    dp[i][j] = firstMatch && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

}







