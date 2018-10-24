package com.yunwoo.cybershop.web.service.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yunwoo.cybershop.dto.UserDTO;
import com.yunwoo.cybershop.dubbo.UserQueryDubboService;
import org.springframework.stereotype.Service;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/5 下午8:01
 */
@Service
public class UserServiceImpl implements UserService{
    @Reference(version = "1.0.0",check = true)
    private UserQueryDubboService userQueryDubboService;
    @Override
    public UserDTO getByUserName(String username) {
        return userQueryDubboService.getByUserName(username);
    }
}
