package com.yunwoo.cybershop.service.member;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yunwoo.cybershop.dubbo.MemberQueryDubboService;
import org.springframework.stereotype.Service;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/12 下午3:42
 */
@Service
public class MemberServiceImpl implements  MemberService{
    @Reference(version = "1.0.0",check = true)
    private MemberQueryDubboService memberQueryDubboService;

    @Override
    public String getSession(Integer memberId) {
        return memberQueryDubboService.getSession(memberId);
    }
}
