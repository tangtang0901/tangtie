package com.yunwoo.cybershop.utils;

import java.util.Date;
import java.util.Random;

public class RandomUtil {
	
	public  static synchronized String getRandomNum(int length){
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		Random rand = new Random(new Date().getTime());
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < length; i++){
			result = result * 10 + array[i];
		}
		StringBuffer resultStr = new StringBuffer();
		resultStr.append(result);
		if(resultStr.length() <length){
			int resultCount = length - resultStr.length();
			for(int i=0;i<resultCount;i++){
				resultStr.append("0");
			}
			return resultStr.toString();
		}
		return String.valueOf(result);
	}

	/**
	 * 获取指定长度的随机字母数字组合字符串
	 * @param length
	 * @return
	 */
	public static String getRandomStr(int length){
		int  maxNum = 36;
		int i;
		int count = 0;
		char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer buffer = new StringBuffer("");
		Random r = new Random();
		while(count < length){
			i = Math.abs(r.nextInt(maxNum));
			if (i >= 0 && i < str.length) {
				buffer.append(str[i]);
				count ++;
			}
		}
		return buffer.toString();
	}
}
