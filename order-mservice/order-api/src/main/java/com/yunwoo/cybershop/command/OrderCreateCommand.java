package com.yunwoo.cybershop.command;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateCommand implements Serializable{
    private int id;
    private String remark;



}
