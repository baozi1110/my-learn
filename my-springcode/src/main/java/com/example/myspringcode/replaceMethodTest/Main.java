package com.example.myspringcode.replaceMethodTest;

import com.example.myspringcode.lookupTest.GetBeanTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    /**
     * 用于替换原有的代码逻辑
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext bf = new ClassPathXmlApplicationContext("xml/replaceMethodTest.xml");
        TestChangeMethod getBeanTest = (TestChangeMethod) bf.getBean("testChangeMethod");
        getBeanTest.changeMe();
    }
}
