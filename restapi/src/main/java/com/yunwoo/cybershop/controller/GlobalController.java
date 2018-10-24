package com.yunwoo.cybershop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yunwoo.cybershop.controller.base.BaseController;
import com.yunwoo.cybershop.dubbo.MemberQueryDubboService;
import com.yunwoo.cybershop.utils.VerificationCodeUtil;
import com.yunwoo.cybershop.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/12 下午8:11
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/login")
public class GlobalController  extends BaseController {

    @Reference(version = "1.0.0",check = true)
    private MemberQueryDubboService memberQueryDubboService;
    /**
     * <p>获得随机的验证码图片</br></p>
     * @param request
     * @return
     */

    @RequestMapping(value = "/createImageVerifyCode",method = RequestMethod.GET)
    @ApiOperation(value = "获取base64的JPEG图片验证码", notes = "获取base64图片验证码")
    public ResponseVO<String> createImageVerifyCode(HttpServletRequest request) throws IOException {
        //获取验证码
        String imageVerifyCode = createImageVerifyCode();
        String[] split = imageVerifyCode.split("-");
        String code = split[0];
        log.info("-----------图片验证码："+code+";unique:"+super.getUnique());
        //放入缓存
        memberQueryDubboService.addCaptcha(super.getUnique(),code);
        String base64 = split[1];
        return success(base64);
    }

    /**
     * 获取图片验证码
     * @throws IOException
     */

    public String createImageVerifyCode() throws IOException {

        VerificationCodeUtil vcu = VerificationCodeUtil.getInstance();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 输出图片方法
        ImageIO.write(vcu.getImage(), "JPEG", baos);
        BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        byte[] bytes = baos.toByteArray();
        return vcu.getVerificationCodeValue()+"-"+encoder.encodeBuffer(bytes).trim();
    }
}
