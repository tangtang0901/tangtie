package com.yunwoo.cybershop.storage.db;

import com.yunwoo.cybershop.db.DaoSupport;
import com.yunwoo.cybershop.storage.po.MenuPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuDBStorageImpl implements MenuDBStorage{

	@Autowired
	private DaoSupport dao;
	
	@Override
	public boolean add(MenuPO t) {
		return dao.add(t);
	}

	@Override
	public boolean delete(int id) {
		return dao.delete(MenuPO.class, id);
	}

	@Override
	public boolean update(MenuPO t) {
		return dao.update(t);
	}

	@Override
	public MenuPO get(int id) {
		return dao.get(MenuPO.class, id);
	}


	@Override
	public List<MenuPO> getByPId(int pId) {
		String sql = "select * from menu where pid=? order by sequence asc";
		return dao.queryForList(sql, MenuPO.class, pId);
	}

}
