package com.yunwoo.cybershop.storage.db;

import com.yunwoo.cybershop.db.BaseStorage;
import com.yunwoo.cybershop.dto.MemberDTO;
import com.yunwoo.cybershop.model.Pagination;
import com.yunwoo.cybershop.model.PaginationResult;
import com.yunwoo.cybershop.storage.po.MemberPO;

/**
 * 元素仓储接口
 * @author ALI
 *         at 2017/4/3 0003 23:04
 */
public interface MemberDBStorage extends BaseStorage<MemberPO> {
    PaginationResult<Integer> getByPagination(Pagination pagination);
    /**
     * 根据用户名获取会员
     * @param username
     * @return
     */
    MemberPO getByUsername(String username);

    /**
     * 根据手机号获取用户
     * @param phonenum
     * @return
     */
    MemberPO getByPhonenum(String phonenum);

}
