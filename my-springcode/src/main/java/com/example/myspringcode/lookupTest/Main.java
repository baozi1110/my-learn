package com.example.myspringcode.lookupTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    /**
     * 获取器注入，把一个方法声明为返回某种类型的bean，但实际返回类型在配置文件中是声明，用于解除程序依赖
     * 实际返回类型需要是声明类型的子类
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext bf = new ClassPathXmlApplicationContext("xml/lookupTest.xml");
        GetBeanTest getBeanTest = (GetBeanTest) bf.getBean("getBeanTest");
        getBeanTest.showMe();
    }
}
