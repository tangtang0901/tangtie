package com.yunwoo.cybershop.storage.db;

import com.yunwoo.cybershop.db.DaoSupport;
import com.yunwoo.cybershop.storage.po.PicturePO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 图片仓储实现类
 * @author ALI
 *         at 2017/4/4 0004 0:42
 */
@Repository
public class PictureDBStorageImpl implements PictureDBStorage{
	private static final Logger log = LoggerFactory.getLogger(PictureDBStorageImpl.class);

	@Autowired
	private DaoSupport dao;

	@Override
	public boolean add(PicturePO picturePO)   {
		return dao.add(picturePO);
	}

	@Override
	public boolean delete(int id) {
		return dao.delete(PicturePO.class, id);
	}

	@Override
	public boolean update(PicturePO picturePO) {
		return dao.update(picturePO);
	}

	@Override
	public PicturePO get(int id) {
		return dao.get(PicturePO.class, id);
	}


	@Override
	public PicturePO getByUrl(String url) {
		String sql = "select * from picture where url = ? order by id limit 1";//加limit防止网络原因多次请求导致重复数据
		return dao.queryForObject(sql,PicturePO.class,url);
	}
}
