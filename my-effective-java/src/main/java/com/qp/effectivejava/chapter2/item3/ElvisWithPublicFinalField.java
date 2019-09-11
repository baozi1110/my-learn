package com.qp.effectivejava.chapter2.item3;

/**
 * 成员是final修饰的属性：
 * @author BaoZi
 * @date 2019/9/11 15:41
 * 公共属性方法的主要优点是API明确表示该类是一个单例：公共静态属性是final的，所以它总是包含相同的对象引用。
 * 第二个好处是它更简单。
 */
public class ElvisWithPublicFinalField {
    public static final ElvisWithPublicFinalField INSTANCE =new ElvisWithPublicFinalField();

    private ElvisWithPublicFinalField(){}
    public void leaveTheBuilding(){
        System.out.println("Whoa baby, I'm outta here!");
    }
}
