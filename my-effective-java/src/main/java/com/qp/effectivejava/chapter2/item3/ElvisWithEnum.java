package com.qp.effectivejava.chapter2.item3;

/**
 * 声明单一元素的枚举类
 *     这种方式类似于公共属性方法，但更简洁，提供了免费的序列化机制，
 *     并提供了针对多个实例化的坚固保证，即使是在复杂的序列化或反射攻击的情况下。
 *     这种方法可能感觉有点不自然，但是单一元素枚举类通常是实现单例的最佳方式。
 *     注意，如果单例必须继承Enum以外的父类(尽管可以声明一个Enum来实现接口)，那么就不能使用这种方法。
 * @author BaoZi
 * @date 2019/9/11 16:00
 */
public enum ElvisWithEnum {
    INSTANCE;
    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }
}
