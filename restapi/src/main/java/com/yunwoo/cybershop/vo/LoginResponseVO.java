package com.yunwoo.cybershop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/12 下午6:27
 */
@Getter
@Setter
@ApiModel(description = "登录成功返回信息")
public class LoginResponseVO {
    @ApiModelProperty(value = "会话标识",example =  "asdasdasdasda")
    private String cusSession;
    @ApiModelProperty(value = "会员ID",example =  "19")
    private Integer memberId;
}
