package com.yunwoo.cybershop.dubbo;

import com.yunwoo.cybershop.dto.MenuDTO;
import com.yunwoo.cybershop.dto.UserDTO;
import com.yunwoo.cybershop.dto.UserDTO;

import java.util.List;

public interface UserQueryDubboService {
    UserDTO getByUserName(String username);
}
