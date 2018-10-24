package com.yunwoo.cybershop.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 公用线程池管理类
 * Created by liuyiyang on 16/5/25.
 */
public class ThreadPoolUtil {
    public static ExecutorService executorService=null;

    /**
     * 获得公用线程池
     * 建议每次提交都走这个方法,防止有人杀死线程池
     * @return 线程管理qi
     */
    public static ExecutorService poll(){
        if(executorService == null || executorService.isShutdown()){
            executorService= Executors.newCachedThreadPool();
        }
        return executorService;
    }
}
