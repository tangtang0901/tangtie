package com.yunwoo.cybershop.controller.member;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yunwoo.cybershop.ao.LoginAO;
import com.yunwoo.cybershop.ao.RegisterAO;
import com.yunwoo.cybershop.ao.RegisterGetCodeAO;
import com.yunwoo.cybershop.ao.RegisterValidAO;
import com.yunwoo.cybershop.command.MemberCreateCommand;
import com.yunwoo.cybershop.constant.Gender;
import com.yunwoo.cybershop.constant.TableName;
import com.yunwoo.cybershop.controller.base.BaseController;
import com.yunwoo.cybershop.dto.MemberDTO;
import com.yunwoo.cybershop.dubbo.MemberCommandDubboService;
import com.yunwoo.cybershop.dubbo.MemberQueryDubboService;
import com.yunwoo.cybershop.dubbo.MsgProviderDubboService;
import com.yunwoo.cybershop.dubbo.PrimaryKeyDubboService;
import com.yunwoo.cybershop.utils.StringUtils;
import com.yunwoo.cybershop.utils.ValidateUtils;
import com.yunwoo.cybershop.utils.VerificationCodeUtil;
import com.yunwoo.cybershop.vo.LoginResponseVO;
import com.yunwoo.cybershop.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/13 上午10:06
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/register")
public class RegisterController extends BaseController{
    @Reference(version = "1.0.0",check = true)
    private MemberQueryDubboService memberQueryDubboService;
    @Reference(version = "1.0.0",check = true)
    private MsgProviderDubboService msgProviderDubboService;
    @Reference(version = "1.0.0",check = true)
    private MemberCommandDubboService memberCommandDubboService;
    @Reference(version = "1.0.0",check = true)
    private PrimaryKeyDubboService primaryKeyDubboService;


    @RequestMapping(value = "",method = RequestMethod.POST)
    @ApiOperation(value = "注册", notes = "注册")
    public ResponseVO<Void> register(@RequestBody @Valid RegisterAO registerAO) {
        //验证手机号是否可以注册
        MemberDTO memberDTO = memberQueryDubboService.getByPhonenum(registerAO.getPhonenumber());
        if(memberDTO == null){
            //验证短信验证码是否正确
            String registerCode = memberQueryDubboService.getRegisterCode(registerAO.getPhonenumber());
            if(registerCode == null){
                return super.exception("短信验证码已过期");
            }else if(registerAO.getCaptcha().equals(registerCode)){
                //注册会员
                int memberId = primaryKeyDubboService.generatePrimaryKey(TableName.MEMBER.name());
                MemberCreateCommand command = new MemberCreateCommand(memberId,registerAO.getPhonenumber(),registerAO.getPassword(),"老铁"
                        ,null, Gender.MAN.getCode(),registerAO.getPhonenumber(),"",null,true);
                memberCommandDubboService.create(command);
                return success();
            }
        }
        return super.exception("注册失败");
    }
    @RequestMapping(value = "/validRegister",method = RequestMethod.POST)
    @ApiOperation(value = "注册验证", notes = "注册验证；")
    public ResponseVO<Void> validRegister(@RequestBody @Valid RegisterValidAO registerValidAO) {
        //验证
        String registerCode = memberQueryDubboService.getRegisterCode(registerValidAO.getPhonenumber());
        if(registerValidAO.getCaptcha().equals(registerCode)){
            return super.success();
        }
        return super.exception("验证失败");
    }
    @RequestMapping(value = "/getRegisterCode",method = RequestMethod.GET)
    @ApiOperation(value = "获取注册验证码", notes = "获取注册验证码；")
    public ResponseVO<Void> getRegisterCode( @Valid
                                                    @NotNull(message = "手机号不能为空")
                                                    @ApiParam(value = "手机号",example =  "18076392422",required = true) @RequestParam String phonenumber) {
        //验证手机号是否可以注册
        MemberDTO memberDTO = memberQueryDubboService.getByPhonenum(phonenumber);
        if(memberDTO == null){
            //判断 短信验证码是否在缓存中
            String registerCode = memberQueryDubboService.getRegisterCode(phonenumber);
            if(StringUtils.isNotEmpty(registerCode)){
                return exception("请求过于频繁，请1分钟后再试");
            }
            //发送短信验证码
            String mobileVerifyCode = VerificationCodeUtil.generateMobileVerifyCode();
            boolean sendResult = msgProviderDubboService.sendRegisterVerificationMsg(phonenumber, mobileVerifyCode);
            if(sendResult){
                //放入缓存
                memberQueryDubboService.addRegisterCode(phonenumber,mobileVerifyCode);
            }
            return super.success();
        }
        return super.exception("手机号已存在");
    }
}
