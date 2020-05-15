package com.example.myhutool.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Baozi
 */
@Data
public class UserA {
    private String name;
    private String a;
    private Date date;
    private List<Seq> sqs;
}
