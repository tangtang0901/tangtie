package com.yunwoo.cybershop.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

/**
 * 数字处理工具类
 */
public class NumberUtils extends org.springframework.util.NumberUtils {
	
	/**
	 * 判断值是否为null或者为0
	 * @param number
	 * @return
	 */
	public static boolean isEmpty(Integer number){
		return (number==null || number.intValue()==0);
	}
	
	/**
	 * 判断值不等于null并且不等于0
	 * @param number
	 * @return
	 */
	public static boolean isNotEmpty(Integer number){
		return !isEmpty(number);
	}
	
	/**
	 * 四舍五入保留两位小数
	 * @param number
	 * @return
	 */
	public static double toDouble(double number){
		return Double.valueOf(String.format("%1$.2f", number));
	}
	
	/**
	 * 四舍五入保留三位小数
	 * @param number
	 * @return
	 */
	public static double toDoubleThree(double number){
		return Double.valueOf(String.format("%1$.3f", number));
	}
	/**
	 * 根据指定数据格式获取相应类型的数值
	 * @param d：需要格式化的数据
	 * @param patternPar：需要格式化的形式
	 * @return
	 */
	public static Double getDoubleSpecifiedType(Double d,String pattern){
		DecimalFormat df=new DecimalFormat(pattern);
		String dStr=df.format(d);
		return Double.parseDouble(dStr);
	}
	
	/**
	 * 
	 * @param range
	 * @return
	 */
	public static String formatPriceRange(String range){
		int index = range.indexOf(".");
		if(index > -1){
			range = range.substring(0, index);
		}
		return range;
	}
	
	/**
	 * 根据精度获取数据
	 * @param object:必须为数字格式的对象
	 * @param max:最大精度
	 * @param min:最小精度
	 * @return
	 */
	public static String getFormatNumber(Object object,int max,int min){
		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(max);
		format.setMinimumFractionDigits(min);
		return format.format(object).replace(",",""); 
	}
	/**
	 * 默认精度为2
	 * @param object：必须为数字格式的对象
	 * @return ：返回精度为2的数据
	 */
	public static String getFormatNumber(Object object){
		return getFormatNumber(object,2,2); 
	}
	
	/**
	 * 将BigDecimal转化为数字类型，保留两位小数，如果number为null，则返回null
	 * @param number
	 * @return
	 */
	public static String toDouble(BigDecimal number){
		if(number==null){
			return "0.00";
		}
		return String.format("%1$.2f", number);
	}
	
	/**
	 * 将BigDecimal转化为保留两位小数的BigDecimal
	 * @param original
	 * @return
	 */
	public static BigDecimal transform(BigDecimal original) {
		String str = toDouble(original);
		return new BigDecimal(str);
	}
	
	/**   
     * 提供精确的加法运算。   
     * @param v1 被加数   
     * @param v2 加数   
     * @return 两个参数的和   
     */    
    public static double add(double v1,double v2){    
        BigDecimal b1 = new BigDecimal(Double.toString(v1));    
        BigDecimal b2 = new BigDecimal(Double.toString(v2));    
        return b1.add(b2).doubleValue();    
    }    
       
    /**   
     * 提供精确的减法运算。   
     * @param v1 被减数   
     * @param v2 减数   
     * @return 两个参数的差   
     */    
    public static double sub(double v1,double v2){    
        BigDecimal b1 = new BigDecimal(Double.toString(v1));    
        BigDecimal b2 = new BigDecimal(Double.toString(v2));    
        return b1.subtract(b2).doubleValue();    
    }    
       
    /**   
     * 提供精确的乘法运算。   
     * @param v1 被乘数   
     * @param v2 乘数   
     * @return 两个参数的积   
     */    
    public static double mul(double v1,double v2){    
        BigDecimal b1 = new BigDecimal(Double.toString(v1));    
        BigDecimal b2 = new BigDecimal(Double.toString(v2));    
        return b1.multiply(b2).doubleValue();    
    }
    
    /**
     * 按输入位数, 生成随机数
     * @param length	位数, 如输入5, 则格式为: 62285
     * @return
     */
    public static int random(int length) {
    	int result = 0;
    	if(length > 0){
    		StringBuffer buf = new StringBuffer();
    		Random r = new Random();
    		int num = r.nextInt(9)+1;
    		buf.append(num);
    		if(length > 1){
    			length--;
    			for(int i = 0; i < length; i++ ) {
    				num = r.nextInt(10);
    	    		buf.append(num);
    			}
    		}
    		result = Integer.parseInt(buf.toString());
    	}
		return result;
    }
    
    /**
     * 填充数字为字符串, 如123, 填充位数6, 则结果为 000123
     * @param length	填充位数
     * @param number	填充数字
     * @return
     */
    public static String padNum(int length, int number){
    	StringBuffer buf = new StringBuffer();
    	String numberStr = number + "";
    	int numberLength = numberStr.length();
    	if(length >= numberLength){
    		int size = length - numberLength;
    		for(int i = 0; i < size; i++ ) {
    			buf.append("0");
    		}
    		buf.append(number);
    	}
    	return buf.toString();
    }
  
}
