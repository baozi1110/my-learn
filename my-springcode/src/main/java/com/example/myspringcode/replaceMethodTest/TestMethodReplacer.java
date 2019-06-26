package com.example.myspringcode.replaceMethodTest;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

// 改变原有的逻辑
public class TestMethodReplacer implements MethodReplacer {
    /**
     * Reimplement the given method.
     *
     * @param obj    the instance we're reimplementing the method for
     * @param method the method to reimplement
     * @param args   arguments to the method
     * @return return value for the method
     */
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("替换了原有代码");
        return null;
    }
}
