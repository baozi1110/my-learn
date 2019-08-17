package cn.qp.myleetcode.code;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class FindMedianSortedArrays {
    /**
     * 将两个数组在任一位置分成两个部分，
     * 将两个左部分合并为left_part，两个右部分合并为right_part，
     * 如果
     * 1.len(left_part)=len(right_part)
     * 2.max(left_part) <=min(right_part})
     * 那么就可以认为中位数是 (max(left_part)+min(right_part))/2
     * -----------------------------------------------------------
     * i和j分别是AB中的分割点，如果
     * 1.i+j=m−i+n−j（或：m - i + n - j + 1m−i+n−j+1）
     * 如果 n ≥m，只需要使i = 0 ~ m,j={m + n + 1}/2 - i
     * 2.B[j−1]≤A[i] 且 A[i-1]≤B[j]
     * 那么以上中位数成立
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        //使m<=n永远成立，如果m>n，那么把A和B交换位置
        if (m > n) {
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0;
        int iMax = m;
        int halfLen = (m + n + 1)/2;
        //iMin和iMax是i取值范围
        while (iMin <= iMax) {
            //i为0~m中的某个数，以使
            //B[j−1]≤A[i] 且 A[i-1]≤B[j],A[i−1]≤B[j]
            //为i、j赋初始值
            int i = (iMin + iMax)/2;
            int j = halfLen - i;
            //i过小，增大i
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1;
            } else if (i > iMin && A[i - 1] > B[j]) {
                // i过大
                iMax = i - 1;
            } else {
                // 找到了目标i，停止搜索
                int maxLeft = 0;
                if (i == 0) {
                    //i为0，说明左部分全是B的元素
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                }else {
                    maxLeft = Math.max(A[i-1],B[j-1]);
                }
                //如果总长度是奇数，那么中位数是集合中的数字，maxLeft==minRight
                if ((m+n)%2==1){
                    return maxLeft;
                }
                int minRight = 0;
                if (i==m){
                    minRight=B[j];
                }else if (j == n) {
                    minRight = A[i];
                }else {
                    minRight = Math.min(B[j],A[i]);
                }
                return (maxLeft+minRight)/2.0;
            }
        }
        return 0.0;
    }

}













