package com.qp.javabasis.lambda;

import java.util.function.Function;

/**
 * Function<T,R> 接收一个输入参数，返回一个结果
 *
 * @author BaoZi
 * @date 2019/9/11 16:47
 */
public class FunctionDemo {
    //apply方法使用，接受输入参数，对输入执行所需操作后  返回一个结果。
    static int modifyTheValue(int valueToBeOperated, Function<Integer, Integer> function) {
        return function.apply(valueToBeOperated);
    }

    //andThen: 返回一个 先执行before函数对象apply方法，再执行当前函数对象apply方法的 函数对象。
    public static Integer modifyTheValue2(int value, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        //value作为function1的参数，返回一个结果，该结果作为function2的参数，返回一个最终结果
        return function1.andThen(function2).apply(value);
    }

    public static void main(String[] args) {
        int number = 10;

        // 使用lambda表达式实现函数式接口
        // (x)->(x)+20 输入一个参数x，进行加法运算，返回一个结果
        // 所以该lambda表达式可以实现Function接口
        int res1 = modifyTheValue(number, (x) -> x + 20);
        System.out.println(res1);

        // 使用匿名内部类实现
        int res2 = modifyTheValue(number, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + 20;
            }
        });
        System.out.println(res2);

        //andThen 先除以2，再加3
        System.out.println(modifyTheValue2(3, val -> val/2, val -> val + 3));
    }
}
