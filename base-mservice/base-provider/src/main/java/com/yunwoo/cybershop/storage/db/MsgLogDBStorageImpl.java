package com.yunwoo.cybershop.storage.db;

import com.yunwoo.cybershop.db.BaseStorage;
import com.yunwoo.cybershop.db.DaoSupport;
import com.yunwoo.cybershop.storage.po.MsgLogPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 短息日志
 */
@Repository
public class MsgLogDBStorageImpl implements MsgLogDBStorage{
    @Autowired
    private DaoSupport dao;
    @Override
    public boolean add(MsgLogPO msgLogPO) {
        return dao.add(msgLogPO);
    }
}
