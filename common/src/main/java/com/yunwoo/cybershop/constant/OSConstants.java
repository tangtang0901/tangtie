package com.yunwoo.cybershop.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作系统常量
 * Created by Fox on 2017/6/15.
 */
public final class OSConstants {
    public static final String ANDROID = "ANDROID";
    public static final String IOS = "IOS";

    public static List<String> list = new ArrayList<String>();
    static {
        list.add(ANDROID);
        list.add(IOS);
    }
}
