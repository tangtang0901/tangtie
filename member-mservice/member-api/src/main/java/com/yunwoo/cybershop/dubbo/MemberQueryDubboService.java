package com.yunwoo.cybershop.dubbo;

import com.yunwoo.cybershop.dto.MemberDTO;
import com.yunwoo.cybershop.model.Pagination;
import com.yunwoo.cybershop.model.PaginationResult;

public interface MemberQueryDubboService {
    MemberDTO getById(int id);
    PaginationResult<MemberDTO> getByPagination(Pagination pagination);
    /**
     * 根据用户名获取会员
     * @param username
     * @return
     */
    MemberDTO getByUsername(String username);

    /**
     * 根据手机号获取用户
     * @param phonenum
     * @return
     */
    MemberDTO getByPhonenum(String phonenum);

    /**
     * 添加登录失败次数
     * @param memberId
     * @return
     */
    void addLoginFailCount(Integer memberId);

    /**
     * 获取登录失败计数
     * @param memberId
     * @return
     */
    Long getLoginFailCount(Integer memberId);

    /**
     * 清除登录失败计数
     * @param memberId
     * @return
     */
    void delLoginFailCount(Integer memberId);

    /**
     * 将验证码放入缓存
     * @param unique
     * @param captcha
     */
    void addCaptcha(String unique,String captcha);
    /**
     * 清除验证码缓存
     * @param unique
     */
    void delCaptcha(String unique);

    String getCaptcha(String unique);

    /**
     * 生成会话
     * @param memberId
     * @param unique
     * @return
     */
    String createSession(Integer memberId, String unique);
    /**
     * 获取会话
     * @param memberId
     * @return
     */
    String getSession(Integer memberId);


    /**
     * 将注册短信验证码放入缓存
     * @param phonenumber
     * @param captcha
     */
    void addRegisterCode(String phonenumber,String captcha);

    /**
     * 获取缓存中注册短信验证码
     * @param phonenumber
     */
    String getRegisterCode(String phonenumber);

}
