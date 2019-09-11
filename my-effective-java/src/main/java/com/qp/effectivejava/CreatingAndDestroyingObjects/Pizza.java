package com.qp.effectivejava.CreatingAndDestroyingObjects;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * 类层次结构的建造者模式
 *
 * @author BaoZi
 * @date 2019/9/11 10:10
 */
public abstract class Pizza {
    public enum Topping {
        HAM, MUSHROOM, ONION, PEPPER, SAUSAGE
    }
    final Set<Topping> toppings;
    abstract static class Builder<T extends Builder<T>>{
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        public T addToping(Topping topping){
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
