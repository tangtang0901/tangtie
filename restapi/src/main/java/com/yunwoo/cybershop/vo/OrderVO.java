package com.yunwoo.cybershop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "订单VO")
public class OrderVO {
    @ApiModelProperty(value = "订单ID",example =  "19")
    private Integer id;
    @ApiModelProperty(value = "订单备注",example =  "注意隐私")
    private String remark;
}
