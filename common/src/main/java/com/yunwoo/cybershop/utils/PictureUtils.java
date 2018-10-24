package com.yunwoo.cybershop.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 图片工具
 * Created by Fox on 2017/4/20.
 */
public class PictureUtils {

    public static final String PIC_DOMAIN = PropertiesUtil.getProperty("picDomain", "common.properties");
    public static final String ASSETS="assets";
    public static final String UPLOAD="upload";
    public static final String SPRIT="/";

    /**
     * 组合图片url
     * @param purePicUrl 纯净的图片url
     * @return
     */
    public static String assemblePicUrl(String purePicUrl){
        if(StringUtils.isBlank(purePicUrl)) return null;
        return PIC_DOMAIN+SPRIT+ASSETS+SPRIT+UPLOAD+SPRIT+purePicUrl;
    }
    /**
     * 组合图片url不带域名
     * @param purePicUrl 纯净的图片url
     * @return
     */
    public static String assemblePicUrlWithoutDomain(String purePicUrl){
        if(StringUtils.isBlank(purePicUrl)) return null;
        return SPRIT+ASSETS+SPRIT+UPLOAD+SPRIT+purePicUrl;
    }

    /**
     * 获取图片保存相对根路径，如/assets/upload/
     * @return
     */
    public static String getPictureRootPath(){
        return SPRIT+ASSETS+SPRIT+UPLOAD+SPRIT;
    }

    /**
     * 获取图片拓展名
     * @param pictureName
     * @return
     */
    public static String getExtension(String pictureName){
        int index = pictureName.lastIndexOf('.');
        if(index > -1){
            String extension = pictureName.substring(index);
            if(checkExtension(extension)){
                return extension;
            }
        }
        return null;
    }

    public static boolean checkExtension(String extension) {
        String regexp = "^.(jpg|jpeg|png|gif)$";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(extension.toLowerCase());
        return matcher.matches();
    }


}
