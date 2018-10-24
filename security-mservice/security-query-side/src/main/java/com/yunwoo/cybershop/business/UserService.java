package com.yunwoo.cybershop.business;

import com.yunwoo.cybershop.dto.UserDTO;

public interface UserService {

	UserDTO getByUserName(String username);
}
