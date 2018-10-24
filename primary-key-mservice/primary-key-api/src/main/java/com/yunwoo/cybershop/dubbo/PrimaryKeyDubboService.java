package com.yunwoo.cybershop.dubbo;

import com.yunwoo.cybershop.constant.TableName;

public interface PrimaryKeyDubboService {
    /**
     * 根据PO对象生成主键
     * @param obj
     * @return
     */
    int generate(Object obj);

    /**
     * 根据表名称生成主键
     * @param tableName
     * @return
     */
    int generatePrimaryKey(String tableName);
    /**
     * 初始化表的主键起始值
     * @param tableName 表名
     * @param value 起始值
     */
    void init(String tableName,int value);

    /**
     * 当前表的ID
     * @param tableName
     * @return
     */
    int getCurrent(String tableName);
}
