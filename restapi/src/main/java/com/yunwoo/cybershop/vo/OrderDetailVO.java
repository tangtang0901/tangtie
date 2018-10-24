package com.yunwoo.cybershop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/6 下午4:52
 */
@Getter
@Setter
@ApiModel(description = "订单详情VO")
public class OrderDetailVO {
    @ApiModelProperty(value = "订单vo",dataType = "com.yunwoo.cybershop.vo.OrderVO")
    private OrderVO orderVO;
}
