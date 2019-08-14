package com.example.myspringcode.parseXml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;


public class parseElementTest {
    public static void main(String[] args) throws DocumentException {
        String xmlStr = "<root><a>test</a></root>";
        Document document = DocumentHelper.parseText(xmlStr);
        Element root = document.getRootElement();// 获得根节点；

        // 进行迭代；读取根节点下的所有节点
        for (Iterator<Element> i = root.elementIterator(); i.hasNext();) {
            Element element = i.next();
            System.out.println("节点名称：" + element.getName());
            System.out.println("节点值：" + element.getData());
        }
    }
}
