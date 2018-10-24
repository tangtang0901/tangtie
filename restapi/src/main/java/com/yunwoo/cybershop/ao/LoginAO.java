package com.yunwoo.cybershop.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ApiModel(description = "登录AO")
@Getter
@Setter
public class LoginAO {

    @NotNull(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号",example =  "18076392422",required = true)
    private String phonenumber;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "手机号",example =  "123456",required = true)
    private String password;

    @ApiModelProperty(value = "图片验证码",example =  "7888",required = false)
    private String captcha;
}
