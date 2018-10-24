package com.yunwoo.cybershop.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 富文本工具
 * Created by Fox on 2017/6/10.
 */
public class RichTextUtil {
    public static final String PIC_DOMAIN = PropertiesUtil.getProperty("picDomain", "common.properties");
    /**
     * 将富文本中的img标签中的src路径加上图片域名，并加上html必要元素
     * @param richtext
     * @param title
     * @return
     */
    public static String handleRichText2Html(String richtext,String title){
        if(StringUtils.isEmpty(richtext)) return null;
        String regex = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher( richtext );
        while( m.find() ){
            String detailFileName = m.group(1);
            richtext = richtext.replaceAll(detailFileName,PIC_DOMAIN + detailFileName);
        }
        String htmlBegin = "<html><head><title>"+(title == null ? "": title)+"</title></head><body>";
        String htmlEnd = "</body></html>";
        return (htmlBegin+richtext+htmlEnd);
    }
}
