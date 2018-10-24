package com.yunwoo.cybershop.dubbo;

public interface MsgProviderDubboService {
    /**
     * 发送注册短信验证码
     * @param phonenumber 手机号
     * @param code 验证码
     * @return
     */
    boolean sendRegisterVerificationMsg(String phonenumber,String code);
}
