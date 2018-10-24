package com.yunwoo.cybershop.business;

import com.yunwoo.cybershop.constant.TableName;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/5 下午10:59
 */
public interface PrimaryKeyService {
    /**
     * 根据PO对象生成主键
     * @param obj
     * @return
     */
    int generate(Object obj);
    /**
     * 根据po clazz对象生成主键
     * @param clazz
     * @return
     */
    int generateByPOClass(Class clazz);

    /**
     * 根据表名称生成主键
     * @param tableName
     * @return
     */
    int generatePrimaryKey(String tableName);
    /**
     * 根据表名枚举称生成主键
     * @param tableName
     * @return
     */
    int generateByTableName(TableName tableName);

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
