package com.bugjc.java.lib.retry4j;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserInfo implements Serializable {
    private String username;
    private int age;
}
