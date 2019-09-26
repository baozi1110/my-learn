package com.qp.effectivejava.chapter3.item11;

import java.util.HashMap;
import java.util.Map;

/**
 * 011
 * 重写equals方法时同时也要重写hashcode方法
 * @author BaoZi
 * @date 2019/9/17 16:05
 */
public final class PhoneNumber implements Cloneable {
    private final short areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "area code");
        this.prefix   = rangeCheck(prefix,   999, "prefix");
        this.lineNum  = rangeCheck(lineNum, 9999, "line num");
    }

    private static short rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max)
            throw new IllegalArgumentException(arg + ": " + val);
        return (short) val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumber that = (PhoneNumber) o;

        if (areaCode != that.areaCode) return false;
        if (prefix != that.prefix) return false;
        return lineNum == that.lineNum;
    }

//    // hashCode method with lazily initialized cached hash code  (page 53)
   private int hashCode; // Automatically initialized to 0
   @Override
   public int hashCode() {
       int result = hashCode;
       if (result == 0) {
           result = Short.hashCode(areaCode);
           result = 31 * result + Short.hashCode(prefix);
           result = 31 * result + Short.hashCode(lineNum);
           hashCode = result;
       }
       return result;
   }
    public static void main(String[] args) {
        Map<PhoneNumber, String> m = new HashMap<>();
        m.put(new PhoneNumber(707, 867, 5309), "Jenny");
        //不重写hashCode取出的值为null
        System.out.println(m.get(new PhoneNumber(707, 867, 5309)));
    }
}
