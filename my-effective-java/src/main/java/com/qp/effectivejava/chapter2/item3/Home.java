package com.qp.effectivejava.chapter2.item3;

import java.util.function.Function;

/**
 * 使用私有构造方法或枚类实现Singleton属性
 * @author BaoZi
 * @date 2019/9/11 15:4
 *
 * 有两种常见的方法来实现单例。
 * 两者都基于保持构造方法私有和导出公共静态成员以提供对唯一实例的访问。
 * 在第一种方法中，成员是final修饰的属性：
 * @see ElvisWithPublicFinalField
 * 在第二个方法中，公共成员是一个静态的工厂方法：
 * @see ElvisWithStaticFactory
 * 第三种方法是声明单一元素的枚举类:单一元素枚举类通常是实现单例的最佳方式
 * @see ElvisWithEnum
 */
public class Home {
    public static void main(String[] args) {
        //使用final修饰公共字段来实现单例
        ElvisWithPublicFinalField elvis = ElvisWithPublicFinalField.INSTANCE;
        elvis.leaveTheBuilding();
        //静态的工厂方法
        ElvisWithStaticFactory elvis1 = ElvisWithStaticFactory.getInstance();
        elvis1.leaveTheBuilding();
        //单一元素枚举类
        ElvisWithEnum elvis2 = ElvisWithEnum.INSTANCE;
        elvis2.leaveTheBuilding();

        Function<Integer,String> fun = (x) -> String.valueOf(x);
        String res = fun.apply(1000);
        System.out.println(res);
    }
}
