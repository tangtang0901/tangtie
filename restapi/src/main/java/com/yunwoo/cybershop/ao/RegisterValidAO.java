package com.yunwoo.cybershop.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(description = "注册验证AO")
@Getter
@Setter
public class RegisterValidAO {

    @NotNull(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号",example =  "18076392422",required = true)
    private String phonenumber;

    @NotNull(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码",example =  "7888",required = true)
    private String captcha;
}
