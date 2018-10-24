package com.yunwoo.cybershop.storage.db;

import com.yunwoo.cybershop.db.BaseStorage;
import com.yunwoo.cybershop.storage.po.MenuPO;

import java.util.List;

public interface MenuDBStorage extends BaseStorage<MenuPO> {

	/**
	 * 根据父ID获取菜单
	 * @param pId
	 * @return
	 */
	List<MenuPO> getByPId(int pId);
}
