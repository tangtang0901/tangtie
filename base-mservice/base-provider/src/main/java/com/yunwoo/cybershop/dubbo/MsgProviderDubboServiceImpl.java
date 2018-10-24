package com.yunwoo.cybershop.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.yunwoo.cybershop.business.MsgService;
import com.yunwoo.cybershop.constant.MsgTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Service(version = "1.0.0",timeout = 20000)
public class MsgProviderDubboServiceImpl implements MsgProviderDubboService{
    @Autowired
    private MsgService msgService;
    /**
     * 发送注册短信验证码
     * @param phonenumber 手机号
     * @param code 验证码
     * @return
     */
    @Override
    public boolean sendRegisterVerificationMsg(String phonenumber, String code){
        Map<String,String> params = new HashMap<String, String>(1);
        params.put("code",code);
        return msgService.sendMsg(phonenumber, MsgTemplate.REGISTER,params);
    }
}
