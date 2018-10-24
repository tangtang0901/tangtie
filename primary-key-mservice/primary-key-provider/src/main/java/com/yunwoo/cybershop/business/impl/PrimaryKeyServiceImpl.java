package com.yunwoo.cybershop.business.impl;

import com.yunwoo.cybershop.annotation.table;
import com.yunwoo.cybershop.business.PrimaryKeyService;
import com.yunwoo.cybershop.constant.TableName;
import com.yunwoo.cybershop.utils.StringUtils;
import com.yunwoo.cybershop.annotation.table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/5 下午11:00
 */
@Service
public class PrimaryKeyServiceImpl implements PrimaryKeyService {
    private static final String SEQ_PREFFIX = "SEQ#";
    Map<String,String> tableNameMap = new ConcurrentHashMap<String,String>();
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public int generate(Object obj) {
        String simpleName = obj.getClass().getSimpleName();
        String tableName = tableNameMap.get(simpleName);
        if(StringUtils.isBlank(tableName)){
            tableName = obj.getClass().getAnnotation(table.class).name();
            if(StringUtils.isNotBlank(tableName)) {
                tableNameMap.put(simpleName, tableName);
            }
        }
        return generatePrimaryKey(tableName);
    }

    @Override
    public int generateByPOClass(Class clazz) {
        String tableName = ((table)clazz.getAnnotation(table.class)).name();
        return generatePrimaryKey(tableName);
    }

    @Override
    public int generatePrimaryKey(String tableName){
        RedisAtomicLong counter = new RedisAtomicLong(SEQ_PREFFIX +tableName, redisTemplate.getConnectionFactory());
        return (int)counter.addAndGet(1);
    }

    @Override
    public int generateByTableName(TableName tableName) {
        return generatePrimaryKey(tableName.name());
    }

    @Override
    public void init(String tableName,int value) {
        RedisAtomicLong counter = new RedisAtomicLong(SEQ_PREFFIX +tableName, redisTemplate.getConnectionFactory());
        counter.set(value);
    }

    @Override
    public int getCurrent(String tableName) {
        RedisAtomicLong counter = new RedisAtomicLong(SEQ_PREFFIX +tableName, redisTemplate.getConnectionFactory());
        return (int)counter.get();
    }
}
