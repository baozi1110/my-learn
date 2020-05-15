package com.example.myhutool.entity;

import lombok.Data;

/**
 * @author Baozi
 */
@Data
public class Seq {
    private  String name;

    public Seq(String name) {
        this.name = name;
    }
}
