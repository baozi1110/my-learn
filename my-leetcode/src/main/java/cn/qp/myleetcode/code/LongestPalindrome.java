package cn.qp.myleetcode.code;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。
 * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-bao-gu/
 */
public class LongestPalindrome {
    public static void main(String[] args) {

    }

    /**
     * 暴力破解，列举所有的子串，判断是否为回文串，保存最长的回文串,在LeetCode中或超出时间限制
     * 时间复杂度 O(n²)，空间复杂度O(1)
     */
    public String longestPalindrome1(String s) {
        String ans = "";
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                String test = s.substring(i, j);
                if (isPalindromic(test) && test.length() > max) {
                    ans = s.substring(i, j);
                    max = Math.max(ans.length(), max);
                }
            }
        }
        return ans;
    }

    public boolean isPalindromic(String s) {
        int len = s.length();
        for (int i = 0; i < len/2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 暴力破解优化
     * 如果 P(i,j)=true，那么s[i.j]是回文串，可以推导出
     * P(i,j)=(P(i+1,j-1)&&S[i]==S[j])
     */
    public String longestPalindrome1Pro(String s) {
        int length = s.length();
        // P是判断s[i.j]是否为回文串
        boolean[][] P = new boolean[length][length];
        int maxLen = 0;
        String maxPal = "";
        //遍历所有的长度
        for (int len = 1; len <= length; len++) {
            for (int start = 0; start < length; start++) {
                int end = start + len - 1;
                //下标越界
                if (end >= length) {
                    break;
                }
                //当长度为1或2是不判断P[start + 1][end - 1])，因为i会大于j
                P[start][end] = (len == 1 || len == 2 || P[start + 1][end - 1]) && (s.charAt(start) == s.charAt(end));
                if (P[start][end] && len > maxLen) {
                    maxPal = s.substring(start, end + 1);
                }
            }
        }
        return maxPal;
    }

    /**
     * 暴力破解优化空间复杂度
     * 当求第 i 行的时候我们只需要第 i+1 行的信息，
     * 并且 j 的话需要 j - 1的信息，所以i/j 需要倒叙。
     */
    public String longestPalindrome1Pro1(String s) {
        int n = s.length();
        String res = "";
        boolean[] P = new boolean[n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= i; j--) {
                P[j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || P[j - 1]);
                if (P[j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }


    /**
     * 最长公共字符串，将原字符串翻转，进行比对   abac/caba 公共字符串为aba
     * 最长公共子串后，并不一定是回文串，我们还需要判断该字符串倒置前的下标和当前的字符串下标是不是匹配。
     * 时间复杂度O(n²)，空间复杂度O(n²)
     */
    public String longestPalindrome2(String s) {
        if (s.equals("")) {
            return "";
        }
        String origin = s;
        //翻转字符串
        String reverse = new StringBuffer(s).reverse().toString();
        int length = s.length();
        // arr中保存的在该位置的公共字符串长度
        int[][] arr = new int[length][length];
        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (origin.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        arr[i][j] = 1;
                    } else {
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                    }
                }
                if (arr[i][j] > maxLen) {
                    //判断下标是否对应
                    int beforeRev = length - 1 - j;
                    if (beforeRev + arr[i][j] - 1 == i) {
                        maxLen = arr[i][j];
                        //以i作为位置结尾的字符
                        maxEnd = i;
                    }
                }
            }
        }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
    }

    /**
     * 最长公共字符串优化，反向遍历，更新每列信息的时候只需要上一列的，所以不用全部保存
     * 降低了空间复杂度，时间复杂度不变
     * 时间复杂度O(n²)，空间复杂度O(n)
     */
    public String longestPalindrome2Pro(String s) {
        if (s.equals("")) {
            return "";
        }
        String origin = s;
        String reverse = new StringBuffer(s).reverse().toString();
        int length = s.length();
        int[] arr = new int[length];
        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 0; i < length; i++) {
            for (int j = length - 1; j >= 0; j--) {
                if (origin.charAt(i) == reverse.charAt(j)) {
                    if (j == 0 || i == 0) {
                        arr[j] = 1;
                    } else {
                        arr[j] = arr[j - 1] + 1;
                    }
                } else {
                    arr[j] = 0;
                }
                if (arr[j] > maxLen) {
                    int beforeRev = length - 1 - j;
                    if (beforeRev + arr[j] - 1 == i) {
                        maxLen = arr[j];
                        maxEnd = i;
                    }
                }
            }
        }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
    }


    /**
     * 中心扩展算法
     * 时间复杂度O(n²)，空间复杂度O(1)
     */
    public String longestPalindrome4(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            //奇数个，取i为中心点
            int len1 = expandAroundCenter(s, i, i);
            //偶数个，取i和i+1的中心为中心点
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1)/2;
                end = i + len/2;
            }
        }
        return s.substring(start, end + 1);
    }

    // 求出回文长度
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }


    /**
     * 马拉车算法
     * 1.首先我们解决下奇数和偶数的问题，在每个字符间插入 "#"，并且为了使得扩展的过程中，
     * 到边界后自动结束，在两端分别插入 "^" 和 "$"，两个不可能在字符串中出现的字符，
     * 这样中心扩展的时候，判断两端字符是否相等的时候，如果到了边界就一定会不相等，从而出了循环。
     * 经过处理，字符串的长度永远都是奇数了。
     * 2.首先我们用一个数组 P 保存从中心扩展的最大个数，而它刚好也是去掉 "#" 的原字符串的总长度。
     * 例如下图中下标是 6 的地方，可以看到 P[ 6 ] 等于 5，所以它是从左边扩展 5 个字符，
     * 相应的右边也是扩展 5 个字符，也就是 "#c#b#c#b#c#"。
     * 而去掉 # 恢复到原来的字符串，变成 "cbcbc"，它的长度刚好也就是 5。
     *
     *
     */
    public String longestPalindrome5(String s) {
        String T = preProcess(s);
        int n = T.length();
        // 用于保存从中心扩展的最大个数，P[6]=5代表下标为6的位置，左右两边都扩展5个字符
        int[] P = new int[n];
        // C为回文串中心，R为右半径
        int C = 0, R = 0;
        for (int i = 1; i < n - 1; i++) {
            // i_mirror是i关于C对称点,利用回文串的对称性，P[i]==P[i_mirror]
            int i_mirror = 2*C - i;
            if (R > i) {
                //当超出R后，i和i_mirror就不能通过对称性获取了，
                P[i] = Math.min(R - i, P[i_mirror]);
            }else {
                //R==i时
                P[i] = 0;
            }
            //有三种情况直接赋值为 P [i_mirror] 是不正确的，此时用中心扩展法一步步扩展即可
            while (T.charAt(i+1+P[i])==T.charAt(i-1-P[i])){
                P[i]++;
            }
            //判断是否需要更新R
            if (i+P[i]>R){
                C=i;
                R=i+P [i];
            }
        }
        //找出P的最大值
        int maxLen= 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i]>maxLen){
                maxLen = P [i];
                centerIndex=i;
            }
        }
        //用 P 的下标 i 减去 P [ i ]，再除以 2，就是原字符串的开头下标了。
        int start = (centerIndex-maxLen)/2;
        return s.substring(start,start+maxLen);

    }

    // 插入#号和首尾字符
    private String preProcess(String s) {
        int n = s.length();
        if (n == 0) {
            return "^$";
        }
        String ret = "^";
        for (int i = 0; i < n; i++) {
            ret += "#" + s.charAt(i);
        }
        ret += "#$";
        return ret;
    }


}








