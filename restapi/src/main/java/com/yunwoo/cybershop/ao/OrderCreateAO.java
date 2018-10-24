package com.yunwoo.cybershop.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ApiModel(description = "创建订单AO")
@Getter
@Setter
public class OrderCreateAO {
    @NotNull(message = "订单备注不能为空")
    @ApiModelProperty(value = "订单备注",example =  "注意隐私",required = true)
    private String remark;
}
