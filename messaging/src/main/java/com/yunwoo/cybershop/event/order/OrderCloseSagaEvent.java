package com.yunwoo.cybershop.event.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class OrderCloseSagaEvent implements Serializable {
    private static final long serialVersionUID = -6449731052912154892L;
    private int id;
    private String remark;
}
