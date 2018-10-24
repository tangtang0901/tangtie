package com.yunwoo.cybershop.config;

import com.yunwoo.cybershop.db.DaoSupport;
import com.yunwoo.cybershop.db.DaoSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@Slf4j
@ImportResource({"spring-db.xml"})
public class DBConfig {
    @Bean
    public DaoSupport daoSupport(JdbcTemplate jdbcTemplate){
        return new DaoSupport(jdbcTemplate);
    }
}
