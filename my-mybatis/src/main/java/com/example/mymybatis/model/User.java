package com.example.mymybatis.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Baozi
 */
@Data
public class User {
    private Integer id;
    private String userName;
    private String passWord;
    private String realName;
}
