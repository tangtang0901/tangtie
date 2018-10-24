package com.yunwoo.cybershop.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.yunwoo.cybershop.annotation.table;
import com.yunwoo.cybershop.business.PrimaryKeyService;
import com.yunwoo.cybershop.constant.TableName;
import com.yunwoo.cybershop.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service(version = "1.0.0",timeout = 20000)
public class PrimaryKeyDubboServiceImpl implements PrimaryKeyDubboService{
    @Autowired
    private PrimaryKeyService primaryKeyService;
    @Override
    public int generate(Object obj) {
        return primaryKeyService.generate(obj);
    }

    @Override
    public int generatePrimaryKey(String tableName) {
        return primaryKeyService.generatePrimaryKey(tableName);
    }

    @Override
    public void init(String tableName, int value) {
        primaryKeyService.init(tableName,value);
    }

    @Override
    public int getCurrent(String tableName) {
        return primaryKeyService.getCurrent(tableName);
    }

}
