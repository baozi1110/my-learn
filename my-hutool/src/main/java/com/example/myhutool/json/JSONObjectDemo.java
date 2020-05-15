package com.example.myhutool.json;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.myhutool.entity.Seq;
import com.example.myhutool.entity.UserA;

import java.util.Date;

/**
 * @author Baozi
 */
public class JSONObjectDemo {
    public static void main(String[] args) {
        // json转换
        // String jsonStr = "{\"b\":\"value2\",\"c\":\"value3\",\"a\":\"value1\"}";
        // JSONObject json = JSONUtil.parseObj(jsonStr);
        // System.out.println(json);

        UserA userA = new UserA();
        userA.setName("nameTest");
        userA.setDate(new Date());
        userA.setSqs(CollectionUtil.newArrayList(new Seq(null), new Seq("seq2")));
        JSONObject json = JSONUtil.parseObj(userA, false, true);
        json.setDateFormat("yyyy-MM-dd HH:mm:ss");
        Console.log(json.toStringPretty());

    }
}
