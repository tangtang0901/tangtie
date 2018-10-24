package com.yunwoo.cybershop.service.order;

import com.yunwoo.cybershop.dto.MemberDTO;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/5 下午8:16
 */
public interface OrderService {
    void create(MemberDTO memberDTO);
    MemberDTO getById(int id);
}
