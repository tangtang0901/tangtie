package com.yunwoo.cybershop.web.service.member;

import com.yunwoo.cybershop.dto.MemberDTO;
import com.yunwoo.cybershop.model.Pagination;
import com.yunwoo.cybershop.model.PaginationResult;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/10 下午2:39
 */
public interface MemberService {
    PaginationResult<MemberDTO> getByPagination(Pagination pagination);
    boolean addMember(MemberDTO memberDTO);
    boolean updateMember(MemberDTO memberDTO);

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
     * 启用会员
     * @param memberId
     * @return
     */
    boolean enable(Integer memberId);
    MemberDTO getById(int id);
    /**
     * 禁用会员
     * @param memberId
     * @return
     */
    boolean disable(Integer memberId);
    /**
     * 修改密码
     * @param memberId
     * @return
     */
    boolean changePassword(Integer memberId,String password);
}
