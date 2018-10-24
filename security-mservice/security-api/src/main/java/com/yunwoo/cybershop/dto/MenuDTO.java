package com.yunwoo.cybershop.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class MenuDTO implements Serializable{
    private int id;
    private int pId;
    private String name;
    private int level;
    private int sequence;
    private String url;
    private String icon;
    private List<MenuDTO> children;
}
