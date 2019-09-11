package com.qp.effectivejava.chapter2.item3;

import org.springframework.expression.spel.ast.Elvis;

/**
 * 公共成员是一个静态的工厂方法
 *  静态工厂方法的一个优点是，它可以灵活地改变你的想法，无论该类是否为单例而不必更改其API。
 *  工厂方法返回唯一的实例，但是可以修改，比如，返回调用它的每个线程的单独实例。
 *  第二个好处是，如果你的应用程序需要它，可以编写一个泛型单例工厂（generic singleton factory ）（条目30）。
 *  使用静态工厂的最后一个优点是方法引用可以用supplier，例如Elvis :: instance等同于Supplier<Elvis>。
 *  除非与这些优点相关的，否则公共属性方法是可取的。
 * @author BaoZi
 * @date 2019/9/11 15:52
 */
public class ElvisWithStaticFactory {
    private static final ElvisWithStaticFactory INSTANCE = new ElvisWithStaticFactory();

    private ElvisWithStaticFactory() {
    }

    public static ElvisWithStaticFactory getInstance() {
        return INSTANCE;
    }

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }
}
