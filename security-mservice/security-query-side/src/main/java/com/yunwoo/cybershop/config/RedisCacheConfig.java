package com.yunwoo.cybershop.config;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 以Spring与配置文件来管理的redis缓存配置类
 *
 */
@Configuration
@EnableCaching
public class RedisCacheConfig{
    /**
     *过期时间
     */
    @Value("${redis.expiration}")
    private Long DEFAULT_EXPIRE;
    /**
     * 最大连接数
     */
    @Value("${redis.pool.maxTotal}")
    private Integer MAX_TOTAL;
    /**
     *最大空闲时间
     */
    @Value("${redis.pool.maxIdle}")
    private Integer MAX_IDLE;
    /**
     *每次最大连接数
     */
    @Value("${redis.pool.numTestsPerEvictionRun}")
    private Integer NUM_TESTS_PER_EVICTION_RUN;
    /**
     *释放扫描的扫描间隔
     */
    @Value("${redis.pool.timeBetweenEvictionRunsMillis}")
    private Integer TIME_BETWEEN_EVICTION_RUNS_MILLIS;
    /**
     *连接的最小空闲时间
     */
    @Value("${redis.pool.minEvictableIdleTimeMillis}")
    private Integer MIN_EVICTABLE_IDLE_TIME_MILLIS;
    /**
     *连接控歘按时间多久后释放，当空闲时间>该值且空闲连接>最大空闲连接数时直接释放
     */
    @Value("${redis.pool.softMinEvictableIdleTimeMillis}")
    private Integer SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS;
    /**
     *获得链接时的最大等待毫秒数，小于0：阻塞不确定时间，默认-1
     */
    @Value("${redis.pool.maxWaitMillis}")
    private Long MAX_WAIT_MILLIS;
    /**
     *在获得链接的时候检查有效性，默认false
     */
    @Value("${redis.pool.testOnBorrow}")
    private Boolean TEST_ON_BORROW;
    /**
     *在空闲时检查有效性，默认false
     */
    @Value("${redis.pool.testWhileIdle}")
    private Boolean TEST_WHILE_IDLE;
    /**
     *连接耗尽时是否阻塞，false报异常，true阻塞超时 默认：true
     */
    @Value("${redis.pool.blockWhenExhausted}")
    private Boolean BLOCK_WHEN_EXHAUSTED;
    /**
     *最大重定向数
     */
    @Value("${redis.maxRedirects}")
    private Integer MAX_REDIRECTS;
    /**
     *host1
     */
    @Value("${redis.host1}")
    private String HOST1;
    /**
     *port1
     */
    @Value("${redis.port1}")
    private Integer PORT1;
    /**
     *host1
     */
    @Value("${redis.host2}")
    private String HOST2;
    /**
     *port1
     */
    @Value("${redis.port2}")
    private Integer PORT2;
    /**
     *host1
     */
    @Value("${redis.host3}")
    private String HOST3;
    /**
     *port1
     */
    @Value("${redis.port3}")
    private Integer PORT3;

    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
        redisCacheManager.setDefaultExpiration(DEFAULT_EXPIRE);
        return redisCacheManager;
    }
    @Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(MAX_TOTAL);
        jedisPoolConfig.setMaxIdle(MAX_IDLE);
        jedisPoolConfig.setNumTestsPerEvictionRun(NUM_TESTS_PER_EVICTION_RUN);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(TIME_BETWEEN_EVICTION_RUNS_MILLIS);
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS);
        jedisPoolConfig.setMaxWaitMillis(MAX_WAIT_MILLIS);
        jedisPoolConfig.setTestOnBorrow(TEST_ON_BORROW);
        jedisPoolConfig.setTestWhileIdle(TEST_WHILE_IDLE);
        jedisPoolConfig.setBlockWhenExhausted(BLOCK_WHEN_EXHAUSTED);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(MIN_EVICTABLE_IDLE_TIME_MILLIS);
        return jedisPoolConfig;
    }
    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.setMaxRedirects(MAX_REDIRECTS);

        RedisNode redisNodeOne = new RedisNode(HOST1,PORT1);
        RedisNode redisNodeTwo = new RedisNode(HOST2,PORT2);
        RedisNode redisNodeThree = new RedisNode(HOST2,PORT3);

        redisClusterConfiguration.addClusterNode(redisNodeOne);
        redisClusterConfiguration.addClusterNode(redisNodeTwo);
        redisClusterConfiguration.addClusterNode(redisNodeThree);
        return redisClusterConfiguration;
    }
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration());
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig());
//        jedisConnectionFactory.setHostName(HOST1);
//        jedisConnectionFactory.setPort(PORT1);
        return jedisConnectionFactory;
    }

}
