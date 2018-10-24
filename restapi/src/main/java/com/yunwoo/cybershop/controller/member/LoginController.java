package com.yunwoo.cybershop.controller.member;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yunwoo.cybershop.ao.LoginAO;
import com.yunwoo.cybershop.controller.base.BaseController;
import com.yunwoo.cybershop.dto.MemberDTO;
import com.yunwoo.cybershop.dubbo.MemberQueryDubboService;
import com.yunwoo.cybershop.utils.MD5Util;
import com.yunwoo.cybershop.utils.PictureUtils;
import com.yunwoo.cybershop.utils.StringUtils;
import com.yunwoo.cybershop.utils.VerificationCodeUtil;
import com.yunwoo.cybershop.vo.LoginResponseVO;
import com.yunwoo.cybershop.vo.OrderVO;
import com.yunwoo.cybershop.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/11 下午7:53
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/login")
public class LoginController extends BaseController{

    @Reference(version = "1.0.0",check = true)
    private MemberQueryDubboService memberQueryDubboService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ApiOperation(value = "登录", notes = "登录；code:'04' 代表需要输入验证码,code:'05' 代表验证码不匹配")
    public ResponseVO<LoginResponseVO> login(@RequestBody @Valid LoginAO loginAO) {



        MemberDTO memberDTO = memberQueryDubboService.getByPhonenum(loginAO.getPhonenumber());

        if(memberDTO != null){
            //判断是否登录失败3次
            Long loginFailCount = memberQueryDubboService.getLoginFailCount(memberDTO.getId());
            //没有失败3次
            if(loginFailCount == null || loginFailCount < 3){
                //匹配密码
                if(memberDTO.getPassword().equals(MD5Util.MD5(loginAO.getPassword()))){
                    //清空失败计数
                    memberQueryDubboService.delLoginFailCount(memberDTO.getId());
                    //生成会话并返回
                    return super.success(getLoginResponse(memberDTO.getId(),super.getUnique()));
                }else {
                    //累计失败次数
                    memberQueryDubboService.addLoginFailCount(memberDTO.getId());
                    if(loginFailCount == 2){
                        return super.exception(super.NEED_CAPTCHA,"登录失败三次，请输入验证码");
                    }else {
                        return super.exception("密码错误");
                    }

                }
            //失败三次
            }else{
                //判断图片验证码
                if(StringUtils.isNotEmpty(loginAO.getCaptcha())){
                    String captchaFromCache = memberQueryDubboService.getCaptcha(super.getUnique());
                    //验证图片验证码通过
                    if(loginAO.getCaptcha().equals(captchaFromCache)){
                        //清空验证码缓存
                        memberQueryDubboService.delCaptcha(super.getUnique());
                        //匹配密码
                        if(memberDTO.getPassword().equals(MD5Util.MD5(loginAO.getPassword()))){
                            //生成会话并返回
                            return super.success(getLoginResponse(memberDTO.getId(),super.getUnique()));
                        }else {
                            memberQueryDubboService.addLoginFailCount(memberDTO.getId());
                            return super.exception("密码错误");
                        }
                    }else {
                        return super.exception(super.CAPTCHA_NOT_MATCH,"图片验证码错误");
                    }
                }
                //提示需要验证码
                return super.exception(super.NEED_CAPTCHA,"登录失败三次，请输入验证码");
            }
        }
        return super.exception("用户不存在");

    }

    private LoginResponseVO getLoginResponse(Integer memberId,String unique){
        LoginResponseVO loginResponseVO = new LoginResponseVO();
        loginResponseVO.setCusSession(memberQueryDubboService.createSession(memberId,unique));
        loginResponseVO.setMemberId(memberId);
        return loginResponseVO;
    }


}
