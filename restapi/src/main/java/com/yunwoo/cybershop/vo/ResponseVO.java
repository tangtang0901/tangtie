package com.yunwoo.cybershop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/11 下午8:31
 */
@Getter
@Setter
@AllArgsConstructor
@ApiModel(description = "响应")
public class ResponseVO<T> {
    @ApiModelProperty(value = "自定义响应码",example =  "00")
    private String code;
    @ApiModelProperty(value = "自定义信息",example =  "成功")
    private String msg;
    @ApiModelProperty(value = "数据")
    private T data;

}
