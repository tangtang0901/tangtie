package com.yunwoo.cybershop.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(description = "注册AO")
@Getter
@Setter
public class RegisterAO {

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^1[3,5,7,8]\\d{9}$",message = "请输入11位手机号")
    @ApiModelProperty(value = "手机号",example =  "18076392422",required = true)
    private String phonenumber;

    @NotNull(message = "密码不能为空")
    @Pattern(regexp = "^[\\da-zA-Z!@#$%^&*.]{6,20}$",message = "只允许6-20位英文字母、数字和符号")
    @ApiModelProperty(value = "手机号",example =  "123456",required = true)
    private String password;

    @NotNull(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码",example =  "788822",required = true)
    private String captcha;
}
