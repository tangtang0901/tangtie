package com.yunwoo.cybershop.business;

import com.yunwoo.cybershop.dto.MemberDTO;
import com.yunwoo.cybershop.model.Pagination;
import com.yunwoo.cybershop.model.PaginationResult;

public interface MemberService {
    MemberDTO getById(int id);
    boolean save(MemberDTO dto);
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
    boolean update(MemberDTO dto);

    /**
     * 登录失败计数
     * @param memberId
     */
    void addLoginFailCount(Integer memberId);

    /**
     * 获取登录失败计数
     * @param memberId
     * @return
     */
    Long getLoginFailCount(Integer memberId);
    void delLoginFailCount(Integer memberId);

    /**
     * 放入图片验证码
     * @param unique 设备码
     * @param captcha 验证码
     */
    void addCaptcha(String unique, String captcha);

    /**
     * 获取图片验证码
     * @param unique 设备码
     * @return
     */
    String getCaptcha(String unique);

    String createSession(Integer memberId, String unique);
    String getSession(Integer memberId);
    void delCaptcha(String unique);
    void addRegisterCode(String phonenumber, String captcha);
    String getRegisterCode(String phonenumber);
}
