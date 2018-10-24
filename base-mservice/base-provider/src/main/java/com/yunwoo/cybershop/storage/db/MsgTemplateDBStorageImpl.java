package com.yunwoo.cybershop.storage.db;

import com.yunwoo.cybershop.db.DaoSupport;
import com.yunwoo.cybershop.storage.po.MsgTemplatePO;
import com.yunwoo.cybershop.storage.po.PicturePO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 */
@Repository
public class MsgTemplateDBStorageImpl implements MsgTemplateDBStorage{
	private static final Logger log = LoggerFactory.getLogger(MsgTemplateDBStorageImpl.class);

	@Autowired
	private DaoSupport dao;

	@Override
	public boolean add(MsgTemplatePO picturePO)   {
		return dao.add(picturePO);
	}

	@Override
	public boolean delete(int id) {
		return dao.delete(MsgTemplatePO.class, id);
	}

	@Override
	public boolean update(MsgTemplatePO picturePO) {
		return dao.update(picturePO);
	}

	@Override
	public MsgTemplatePO get(int id) {
		return dao.get(MsgTemplatePO.class, id);
	}

	@Override
	public MsgTemplatePO getByInternalCode(Integer internalCode) {
		String sql = "select * from msg_template where internalCode = ?";
		return dao.queryForObject(sql,MsgTemplatePO.class,internalCode);
	}
}
