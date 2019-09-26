package com.qp.javabasis.lambda;

import java.util.function.Supplier;

/**
 * Supplier供给型函数式接口
 *  无参数，返回一个结果。
 * @author BaoZi
 * @date 2019/9/11 17:36
 */
public class SupplierDemo {
    public static String supplierTest(Supplier<String> supplier) {
        return supplier.get();
    }

    public static void main(String args[]) {
        String name = "冷冷";
        // () -> name.length() 无参数，返回一个结果（字符串长度）
        // 所以该lambda表达式可以实现Supplier接口
        System.out.println(supplierTest(() -> name.length() + ""));
    }
}
