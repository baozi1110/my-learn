package com.qp.javabasis.lambda;
import	java.util.Objects;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author BaoZi
 * @date 2019/9/11 17:39
 */
public class AnotherAnalysis {
    public static void main(String[] args) {
        //Predicate 接受一个输入参数，返回一个布尔值结果
        Predicate<String> predicate = s -> s.length()>0;
        System.out.println(predicate.test("test java 8"));//true
        //取反
        System.out.println(predicate.negate().test("test java 8"));//false
        Predicate<Boolean> noneNull = Objects::nonNull;

        Optional<String> optional = Optional.of("test java 1.8 optional");
        System.out.println(optional.isPresent());
        System.out.println(optional.get());
        System.out.println(optional.orElse("0test java 1.8 optional0"));
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "t"
    }
}
