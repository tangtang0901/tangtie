package com.yunwoo.cybershop.web.service.user;

import com.yunwoo.cybershop.dto.UserDTO;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/5 下午8:00
 */
public interface UserService {
    UserDTO getByUserName(String username);
}
