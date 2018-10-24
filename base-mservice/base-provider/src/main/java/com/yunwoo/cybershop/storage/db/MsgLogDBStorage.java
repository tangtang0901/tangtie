package com.yunwoo.cybershop.storage.db;

import com.yunwoo.cybershop.storage.po.MsgLogPO;

/**
 * 短息日志
 */
public interface MsgLogDBStorage {
    boolean add(MsgLogPO msgLogPO);
}
