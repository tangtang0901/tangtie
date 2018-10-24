package com.yunwoo.cybershop.command;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCloseCommand implements Serializable{
    private int id;
    private String remark;



}
