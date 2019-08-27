package cn.qp.myleetcode.code;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 */
public class MaxArea {

    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        int i = maxArea(height);
        System.out.println(i);
    }

    /**
     * 暴力法
     * 在这种情况下，我们将简单地考虑每对可能出现的线段组合并找出这些情况之下的最大面积。
     */
    public static int maxArea(int[] height) {
        int maxarea = 0;
        for (int i = 0; i < height.length; i++)
            for (int j = i + 1; j < height.length; j++)
                maxarea = Math.max(maxarea, Math.min(height[i], height[j])*(j - i));
        return maxarea;
    }

    /**
     * 使用双指针算法
     *  初始状态由最外围两条线段组成区域，两端分别放两个指针，让短边的指针向长边移动，
     *  比较每次移动后的面积决定是否替换，一直移动到两条线相交
     */
    public  int maxArea1(int[] height) {
        int maxArea=0;
        //定义左右指针
        int l=0;
        int r = height.length-1;
        while (l<r){
            maxArea = Math.max(maxArea, Math.min(height [l], height [r])*(r-l));
            if (height [l]<height [r]){
                l++;
            }else {
                r--;
            }
        }
        return maxArea;
    }


    }
