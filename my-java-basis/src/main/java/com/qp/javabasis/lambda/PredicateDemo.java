package com.qp.javabasis.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * Predicate 接受一个输入参数，返回一个布尔值结果。
 *
 * @author BaoZi
 * @date 2019/9/11 17:16
 */
public class PredicateDemo {
    //使用1：接受一个输入参数，返回一个布尔值结果。
    public static boolean predicateTest(int value, IntPredicate predicate) {
        return predicate.test(value);
    }

    //使用2：查找数组中的特定值
    public static void eval(Iterable<Integer> list, IntPredicate predicate) {
        list.forEach((Integer n) -> {
            if (predicate.test(n)) {
                System.out.print(n + " ");
            }
        });
    }

    public static boolean validInput(String name, Predicate<String> function) {
        return function.test(name);
    }

    public static void main(String[] args) {
        // (x) -> x == 3 输入一个参数x，进行比较操作，返回一个布尔值
        // 所以该lambda表达式可以实现Predicate接口
        // System.out.println(predicateTest(3, (x) -> x == 3));


        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        // Predicate<Integer> predicate = n -> true
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // n 如果存在则 test 方法返回 true
        System.out.println("输出所有数据：");
        //传递参数n
        eval(list, n -> true);

        // Predicate<Integer> predicate1 = n -> n%2 == 0
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n%2 为 0 test 方法返回 true
        System.out.println("\n输出所有偶数:");
        eval(list,n -> n%2==0);

        // Predicate<Integer> predicate2 = n -> n > 3
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n 大于 3 test 方法返回 true
        System.out.println("\n输出大于 3 的所有数字:");
        eval(list, n -> n > 3);

        String name = "冷冷";
        if(validInput(name, s -> !s.isEmpty() &&  s.length() <= 3 )) {
            System.out.println("\n名字输入正确");
        }
    }
}
