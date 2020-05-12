package com.example.mymybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 74360
 */
@MapperScan("com.example.mymybatis.mapper")
@SpringBootApplication(scanBasePackages = {"com.example.mymybatis.*"})
public class MyMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyMybatisApplication.class, args);
    }

}
