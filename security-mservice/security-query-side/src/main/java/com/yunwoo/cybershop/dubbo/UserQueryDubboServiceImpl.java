package com.yunwoo.cybershop.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.yunwoo.cybershop.business.MenuService;
import com.yunwoo.cybershop.business.UserService;
import com.yunwoo.cybershop.dto.MenuDTO;
import com.yunwoo.cybershop.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0",timeout = 20000)
public class UserQueryDubboServiceImpl implements UserQueryDubboService {
    @Autowired
    private UserService userService;

    @Override
    public UserDTO getByUserName(String username) {
        return userService.getByUserName(username);
    }
}
