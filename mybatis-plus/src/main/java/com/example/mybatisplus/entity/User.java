package com.example.mybatisplus.entity;

import lombok.Data;

/**
 * @author Baozi
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
