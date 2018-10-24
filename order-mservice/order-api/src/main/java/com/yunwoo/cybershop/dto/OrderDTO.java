package com.yunwoo.cybershop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Data
public class OrderDTO implements Serializable{

    private int id;
    private String remark;


}
