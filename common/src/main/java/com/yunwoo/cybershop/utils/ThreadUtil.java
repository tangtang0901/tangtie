package com.yunwoo.cybershop.utils;

import org.springframework.util.CollectionUtils;

import java.util.List;

public class ThreadUtil {
	
	public static void run(List<Runnable> list){
		if (!CollectionUtils.isEmpty(list)) {
			for (Runnable runnable : list) {
				Thread t =  new Thread(runnable);
				t.start();
			}
		}

	}
}