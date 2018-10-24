package com.yunwoo.cybershop.storage.db;

import com.yunwoo.cybershop.db.DaoSupport;
import com.yunwoo.cybershop.storage.po.OrderPO;
import com.yunwoo.cybershop.storage.po.OrderPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 元素仓储实现类
 * @author ALI
 *         at 2017/4/3 0003 23:06
 */
@Repository
public class OrderDBStorageImpl implements OrderDBStorage {
	private static final Logger log = LoggerFactory.getLogger(OrderDBStorageImpl.class);

	@Autowired
	private DaoSupport dao;

	@Override
	public boolean add(OrderPO orderPO) {
		return dao.add(orderPO);
	}

	@Override
	public boolean delete(int id) {
		return dao.delete(OrderPO.class, id);
	}

	@Override
	public boolean update(OrderPO orderPO) {
		return dao.update(orderPO);
	}

	@Override
	public OrderPO get(int id) {
		return dao.get(OrderPO.class, id);
	}



}
