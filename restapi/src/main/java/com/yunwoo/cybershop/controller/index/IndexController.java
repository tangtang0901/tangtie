/**
 * 2015-2016 龙果学院 (www.roncoo.com)
 */
package com.yunwoo.cybershop.controller.index;

import com.yunwoo.cybershop.ao.OrderCreateAO;
import com.yunwoo.cybershop.dto.MemberDTO;
import com.yunwoo.cybershop.service.order.OrderService;
import com.yunwoo.cybershop.utils.BeanConverter;
import com.yunwoo.cybershop.vo.OrderDetailVO;
import com.yunwoo.cybershop.vo.OrderVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * spring-boot-demo-2-1
 * 
 * @author wujing
 */
@RestController
@RequestMapping(value = "/api/index")
public class IndexController {

}
