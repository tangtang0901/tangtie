package com.yunwoo.cybershop.utils;

import org.springframework.context.ApplicationContext;

/**
 * 获取spring容器的工具
 */
public final class ApplicationContextUtils {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void init(ApplicationContext applicationContext) {
        if(ApplicationContextUtils.applicationContext == null) ApplicationContextUtils.applicationContext = applicationContext;
    }
}
