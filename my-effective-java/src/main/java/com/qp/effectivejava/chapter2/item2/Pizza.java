package com.qp.effectivejava.chapter2.item2;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * 类层次结构的建造者模式
 *      父类提供添加浇头的类型，子类有各自的单独选项
 * @author BaoZi
 * @date 2019/9/11 10:10
 */
public abstract class Pizza {
    public enum Topping {
        HAM, MUSHROOM, ONION, PEPPER, SAUSAGE
    }
    final Set<Topping> toppings;

    /**
     * Pizza.Builder是一个带有递归类型参数的泛型类型。
     * 这与抽象的self方法一起，允许方法链在子类中正常工作，而不需要强制转换。
     * Java缺乏自我类型的这种变通解决方法被称为模拟自我类型的习惯用法。
     * @param <T>
     */
    abstract static class Builder<T extends Builder<T>>{
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        public T addTopping(Topping topping){
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }
        abstract Pizza build();
        // 子类必须覆盖此方法才能返回“this”
        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        //保护性拷贝
        toppings = builder.toppings.clone();
    }
}
