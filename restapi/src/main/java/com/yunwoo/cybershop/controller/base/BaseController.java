package com.yunwoo.cybershop.controller.base;

import com.yunwoo.cybershop.service.member.MemberService;
import com.yunwoo.cybershop.utils.StringUtils;
import com.yunwoo.cybershop.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Fox on 2017/4/14.
 */
public class BaseController {

    @Autowired
    private HttpServletRequest httpServletRequest;
//
    @Autowired
    private MemberService memberService;
//
    /**
     * 成功
     */
    public  final String STATE_CODE_SUCCESS = "00";
    /**
     * 异常
     */
    public  final String STATE_CODE_EXCEPTION = "01";
    /**
     * 不合法
     */
    public  final String STATE_CODE_INVALID = "02";
    /**
     * 未登录
     */
    public  final String STATE_CODE_NOT_LOGIN = "03";
    /**
     * 需要登录图片验证码
     */
    public  final String NEED_CAPTCHA = "04";
    /**
     * 图片验证码错误
     */
    public  final String CAPTCHA_NOT_MATCH = "05";

    /**
     * 成功
     * @param data
     * @param <T>
     * @return
     */
    protected <T> ResponseVO<T> success(T data){
        return new ResponseVO<>(STATE_CODE_SUCCESS,"",data);
    }

    /**
     * 成功
     * @param <T>
     * @return
     */
    protected <T> ResponseVO<T> success(){
        return new ResponseVO<>(STATE_CODE_SUCCESS,"",null);
    }

    /**
     * 成功
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    protected <T> ResponseVO<T> success(String msg,T data){
        return new ResponseVO<>(STATE_CODE_SUCCESS,msg,data);
    }

    /**
     * 异常
     * @param msg
     * @param <T>
     * @return
     */
    protected <T> ResponseVO<T> exception(String msg){
        return new ResponseVO<>(STATE_CODE_EXCEPTION,msg,null);
    }

    /**
     异常* @param msg
     * @param data
     * @param <T>
     * @return
     */
    protected <T> ResponseVO<T> exception(String msg,T data){
        return new ResponseVO<>(STATE_CODE_EXCEPTION,msg,data);
    }

    /**
     * 异常
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    protected <T> ResponseVO<T> exception(String code,String msg,T data){
        return new ResponseVO<>(code,msg,data);
    }

    /**
     * 异常
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    protected <T> ResponseVO<T> exception(String code,String msg){
        return new ResponseVO<>(code,msg,null);
    }

    /**
     * 业务不合法
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    protected <T> ResponseVO<T> invalid(String msg,T data){
        return new ResponseVO<>(STATE_CODE_INVALID,msg,data);
    }

    /**
     * 业务不合法
     * @param msg
     * @param <T>
     * @return
     */
    protected <T> ResponseVO<T> invalid(String msg){
        return new ResponseVO<>(STATE_CODE_INVALID,msg,null);
    }
    /**
     * 业务不合法
     * @param <T>
     * @return
     */
    protected <T> ResponseVO<T> notLogin(){
        return new ResponseVO<>(STATE_CODE_NOT_LOGIN,"登录状态失效或未登录",null);
    }
    /**
     * 获取当前登陆用户ID
     */
    protected int getMemberId(){
        if(isLogin()) {
            return Integer.valueOf(httpServletRequest.getHeader("memberId"));
        }else{
            return 0;
        }
    }
    /**
     * 当前用户是否登陆
     */
    protected boolean isLogin(){
        String cusSession = httpServletRequest.getHeader("cusSession");
        //如果会话不存在，未登录
        if(StringUtils.isBlank(cusSession)) {
            return false;
        }
        String memberIdStr = httpServletRequest.getHeader("memberId");
        //如果会员ID不存在，未登录
        if(StringUtils.isBlank(memberIdStr)) {
            return false;
        }
        Integer memberId = 0;
        try {
            memberId = Integer.valueOf(memberIdStr);
            String session = memberService.getSession(memberId);
            if(StringUtils.isEmpty(session)) {
                return false;
            }
            boolean cusSessionMatch = session.equals(cusSession) ? true : false;
            return cusSessionMatch;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

    }
//
    /**
     * 获取OS
     * @return
     */
    protected String getOS(){
        return  httpServletRequest.getHeader("os");

    }
    /**
     * 获取设备码
     * @return
     */
    protected String getUnique(){
        return  httpServletRequest.getHeader("unique");

    }

}
