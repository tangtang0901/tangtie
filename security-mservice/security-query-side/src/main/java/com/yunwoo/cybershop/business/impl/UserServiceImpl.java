package com.yunwoo.cybershop.business.impl;

import com.yunwoo.cybershop.business.UserService;
import com.yunwoo.cybershop.dto.UserDTO;
import com.yunwoo.cybershop.storage.db.UserDBStorage;
import com.yunwoo.cybershop.storage.po.UserPO;
import com.yunwoo.cybershop.utils.BeanConverter;
import com.yunwoo.cybershop.storage.db.UserDBStorage;
import com.yunwoo.cybershop.storage.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 后台用户业务
 */
@Service
public class UserServiceImpl implements UserService {
	public static final String CACHE_NAME = "user";
	public static final String CACHE_KEY = "'user#'";
	@Autowired
	private UserDBStorage userDBStorage;
	
	@Override
	@Cacheable(value= CACHE_NAME,key = CACHE_KEY +"+#username")
	public UserDTO getByUserName(String username) {
		UserPO po = userDBStorage.getByUserName(username);
		return BeanConverter.to(po,UserDTO.class);
	}

}
