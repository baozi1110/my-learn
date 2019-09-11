package com.qp.effectivejava.CreatingAndDestroyingObjects;

/**
 * 02 当构造方法参数过多时使用 builder 模式
 *      建造者模式，
 *      客户端不直接调用所需的对象，而是调用构造方法 (或静态工厂)，并使用所有必需的参数，并获得一个 builder 对象。
 *      然后，客户端调用 builder 对象的 setter 相似方法来设置每个可选参数。
 *      最后，客户端调用一个无参的 build 方法来生成对象，该对象通常是不可变的
 * @author BaoZi
 * @date 2019/9/11 9:37
 * @see NutritionFacts
 */
public class ConsiderBuilderWithManyConstructor {
    public static void main(String[] args) {
        NutritionFacts cocoCola = new NutritionFacts.Builder(240,8).
                calories(100).sodium(35).carbohydrate(27).build();
        System.out.println(cocoCola);
    }
}
