package com.example.myspringcode;

import com.example.myspringcode.entity.MyTestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("deprecation")
public class MySpringcodeApplicationTests {

    @Test
    public void contextLoads() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("xml/beanFactoryTest.xml"));
        // 获取bean
        MyTestBean bean = (MyTestBean) bf.getBean("myTestBean");
        assertEquals("testStr",bean.getTestStr());
        // 获取beanDefinition，解析meta标签
        BeanDefinition myTestBean = ((XmlBeanFactory) bf).getBeanDefinition("myTestBean");
        Object keyname = myTestBean.getAttribute("keyname");
        System.out.println(keyname);
    }

}
