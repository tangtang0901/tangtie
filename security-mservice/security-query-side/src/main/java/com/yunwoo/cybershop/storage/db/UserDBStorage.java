package com.yunwoo.cybershop.storage.db;

import com.yunwoo.cybershop.db.BaseStorage;
import com.yunwoo.cybershop.storage.po.UserPO;

public interface UserDBStorage  extends BaseStorage<UserPO> {

	UserPO getByUserName(String userName);
}
