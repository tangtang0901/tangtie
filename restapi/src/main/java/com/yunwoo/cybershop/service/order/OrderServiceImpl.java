package com.yunwoo.cybershop.service.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yunwoo.cybershop.dto.MemberDTO;
import com.yunwoo.cybershop.dubbo.MemberQueryDubboService;
import com.yunwoo.cybershop.dubbo.PrimaryKeyDubboService;
import org.springframework.stereotype.Service;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/5 下午8:19
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Reference(version = "1.0.0",check = true)
    private MemberQueryDubboService memberQueryDubboService;
    @Reference(version = "1.0.0",check = true)
    private PrimaryKeyDubboService primaryKeyDubboService;

    @Override
    public void create(MemberDTO memberDTO) {
    }

    @Override
    public MemberDTO getById(int id) {
       return memberQueryDubboService.getById(id);
    }
}
