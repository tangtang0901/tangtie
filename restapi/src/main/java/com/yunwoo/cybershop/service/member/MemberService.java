package com.yunwoo.cybershop.service.member;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/12 下午3:40
 */
public interface MemberService {
    /**
     * 获取会话
     * @param memberId
     * @return
     */
    String getSession(Integer memberId);
}
