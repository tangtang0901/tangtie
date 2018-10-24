package com.yunwoo.cybershop.storage.db;

import com.yunwoo.cybershop.db.DaoSupport;
import com.yunwoo.cybershop.storage.po.MsgConfigPO;
import com.yunwoo.cybershop.storage.po.MsgTemplatePO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 */
@Repository
public class MsgConfigDBStorageImpl implements MsgConfigDBStorage{
	private static final Logger log = LoggerFactory.getLogger(MsgConfigDBStorageImpl.class);

	@Autowired
	private DaoSupport dao;

	@Override
	public boolean add(MsgConfigPO picturePO)   {
		return dao.add(picturePO);
	}

	@Override
	public boolean delete(int id) {
		return dao.delete(MsgConfigPO.class, id);
	}

	@Override
	public boolean update(MsgConfigPO picturePO) {
		return dao.update(picturePO);
	}

	@Override
	public MsgConfigPO get(int id) {
		return dao.get(MsgConfigPO.class, id);
	}

	@Override
	public MsgConfigPO getByInternalCode(Integer internalCode) {
		String sql = "select * from msg_config where internalCode = ?";
		return dao.queryForObject(sql,MsgConfigPO.class,internalCode);
	}
}
