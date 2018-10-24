package com.yunwoo.cybershop.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ApiModel(description = "获取注册验证码AO")
@Getter
@Setter
public class RegisterGetCodeAO {

    @NotNull(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号",example =  "18076392422",required = true)
    private String phonenumber;

}
