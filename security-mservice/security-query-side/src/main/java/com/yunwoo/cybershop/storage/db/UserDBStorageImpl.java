package com.yunwoo.cybershop.storage.db;

import com.yunwoo.cybershop.db.DaoSupport;
import com.yunwoo.cybershop.storage.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDBStorageImpl implements UserDBStorage{

	@Autowired
	private DaoSupport dao;
	
	@Override
	public boolean add(UserPO t) {
		return dao.add(t);
	}

	@Override
	public boolean delete(int id) {
		return dao.delete(UserPO.class, id);
	}

	@Override
	public boolean update(UserPO t) {
		return dao.update(t);
	}

	@Override
	public UserPO get(int id) {
		return dao.get(UserPO.class, id);
	}


	@Override
	public UserPO getByUserName(String userName) {
		String sql = "select * from user where userName=?";
		return dao.queryForObject(sql, UserPO.class, userName);
	}

}
