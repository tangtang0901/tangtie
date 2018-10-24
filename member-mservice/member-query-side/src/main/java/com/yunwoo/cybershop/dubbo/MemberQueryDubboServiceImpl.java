package com.yunwoo.cybershop.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.yunwoo.cybershop.business.MemberService;
import com.yunwoo.cybershop.dto.MemberDTO;
import com.yunwoo.cybershop.model.Pagination;
import com.yunwoo.cybershop.model.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0",timeout = 20000)
public class MemberQueryDubboServiceImpl implements MemberQueryDubboService {
    @Autowired
    private MemberService memberService;
    @Override
    public MemberDTO getById(int id) {
        return memberService.getById(id);
    }

    @Override
    public PaginationResult<MemberDTO> getByPagination(Pagination pagination) {
        return memberService.getByPagination(pagination);
    }

    @Override
    public MemberDTO getByUsername(String username) {
        return memberService.getByUsername(username);
    }

    @Override
    public MemberDTO getByPhonenum(String phonenum) {
        return memberService.getByPhonenum(phonenum);
    }

    @Override
    public void addLoginFailCount(Integer memberId) {
        memberService.addLoginFailCount(memberId);
    }

    @Override
    public Long getLoginFailCount(Integer memberId) {
        return memberService.getLoginFailCount(memberId);
    }

    @Override
    public void delLoginFailCount(Integer memberId) {
        memberService.delLoginFailCount(memberId);
    }

    @Override
    public void addCaptcha(String unique, String captcha) {
        memberService.addCaptcha(unique,captcha);
    }

    @Override
    public void delCaptcha(String unique) {
        memberService.delCaptcha(unique);
    }

    @Override
    public String getCaptcha(String unique) {
        return memberService.getCaptcha(unique);
    }

    @Override
    public String createSession(Integer memberId, String unique) {
        return memberService.createSession(memberId,unique);
    }

    @Override
    public String getSession(Integer memberId) {
        return null;
    }

    @Override
    public void addRegisterCode(String phonenumber, String captcha) {
        memberService.addRegisterCode(phonenumber,captcha);
    }

    @Override
    public String getRegisterCode(String phonenumber) {
        return memberService.getRegisterCode(phonenumber);
    }
}
