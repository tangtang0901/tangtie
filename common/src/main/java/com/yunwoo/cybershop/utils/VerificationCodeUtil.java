package com.yunwoo.cybershop.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.UUID;

/** 
 * 验证码类，主要生成几种不同类型的验证码  
 * 第一种：简单验证码，4位随机数字  
 * 第二种：英文字符加数字的验证码  
 * 第三种：像铁路订票系统一样的验证码，肆+？=21 
 *
 * @author ALI 2017年3月21日
 */ 
public class VerificationCodeUtil {
	// 图像  
	private BufferedImage image;
	// 验证码  
    private String str;
    // 图像宽度
    private static final int WIDTH = 80;  
    // 图像高度
    private static final int HEIGHT = 40;
    
    private static final Font FONT = new Font("Consolas", Font.BOLD, 20);

    /**
     * 手机动态验证码长度
     */
    private static final int VERIFYCODE_MOBILE = 6;

    /** 
     * 功能：获取一个验证码类的实例 
     *  
     * @return 
     */  
    public static VerificationCodeUtil getInstance() { 
        return new VerificationCodeUtil();  
    }  
  
    private VerificationCodeUtil() {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,  
                BufferedImage.TYPE_INT_RGB);  
        initLetterAndNumVerificationCode(image);
    }  

    /**
     * 生成手机动态验证码
     * 
     * @return 6位随机数字码
     */
    public static String generateMobileVerifyCode() {
    	 Random random = new Random();
    	 StringBuffer mobile_verifyCode = new StringBuffer();
    	 for(int i = 0; i < VERIFYCODE_MOBILE; i++){
    		 mobile_verifyCode.append(random.nextInt(10));
    	 }
    	 return mobile_verifyCode.toString();
    }
    
    /**
     * 生成邮箱验证码
     * 
     * @return
     */
    public static String generateEmailVerifyCode() {
    	return UUID.randomUUID().toString();
    }

    /** 
     * 功能：纯数字验证码
     */  
    public void initNumVerificationCode(BufferedImage image) {  
        Random random = new Random(); // 生成随机类  
        Graphics g = initImage(image, random);  
        String sRand = "";  
        for (int i = 0; i < 4; i++) {  
            String rand = String.valueOf(random.nextInt(10));  
            sRand += rand;  
            // 将认证码显示到图象中  
            g.setColor(new Color(20 + random.nextInt(110), 20 + random  
                    .nextInt(110), 20 + random.nextInt(110)));  
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成  
            g.drawString(rand, 13 * i + 6, 20);  
        }  
        this.setStr(sRand);/* 赋值验证码 */  
        // 图象生效  
        g.dispose();  
        this.setImage(image);  
    }

    /** 
     * 功能：根据字符池来生成
     */  
    public void initLetterAndNumVerificationCode(BufferedImage image) {  
        Random random = new Random(); // 生成随机类  
        Graphics g = initImage(image, random);  
        String[] letter = { "A", "B", "C", "D", "E", "F", "G", "H", "J",  
                "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V",  
                "W", "X", "Y", "Z", 
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",  
                "k", "m", "n", "p", "q", "r", "s", "t", "u", "v",  
                "w", "x", "y", "z",
                "2", "3", "4", "5", "6", "7", "8", "9"}; 
        String sRand = "";  
        for (int i = 0; i < 4; i++) {  
            String tempRand = "";  
            if (random.nextBoolean()) {  
                tempRand = String.valueOf(random.nextInt(10));  
            } else {  
                tempRand = letter[random.nextInt(25)];  
                if (random.nextBoolean()) {// 随机将该字母变成小写  
                    tempRand = tempRand.toLowerCase();  
                }  
            }  
            sRand += tempRand;  
            g.setColor(new Color(20 + random.nextInt(10), 20 + random  
                    .nextInt(110), 20 + random.nextInt(110)));  
            g.drawString(tempRand, 13 * i + 16, 26);  
        }  
        this.setStr(sRand);/* 赋值验证码 */  
        g.dispose(); // 图象生效  
        this.setImage(image);  
    }  
  
    /** 
     * 功能：计算式 即：肆+？=24 
     */  
    public void initChinesePlusNumVerificationCode(BufferedImage image) {  
        String[] cn = { "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾" };  
        Random random = new Random(); // 生成随机类  
        Graphics g = initImage(image, random);  
        int x = random.nextInt(10) + 1;  
        int y = random.nextInt(30);  
        this.setStr(String.valueOf(y));  
        g.setFont(new Font("楷体", Font.PLAIN, 14));// 设定字体  
        g.setColor(new Color(20 + random.nextInt(10), 20 + random.nextInt(110),  
                20 + random.nextInt(110)));  
        g.drawString(cn[x - 1], 1 * 1 + 6, 26);  
        g.drawString("+", 22, 26);  
        g.drawString("？", 35, 26);  
        g.drawString("=", 48, 26);  
        g.drawString(String.valueOf(x + y), 61, 26);
        g.dispose(); // 图象生效  
        this.setImage(image);  
    }  
  
    public Graphics initImage(BufferedImage image, Random random) {  
        Graphics g = image.getGraphics(); // 获取图形上下文  
        g.setColor(getRandColor(200, 250));// 设定背景色  
        g.fillRect(0, 0, WIDTH, HEIGHT);  
        g.setFont(FONT);// 设定字体  
        g.setColor(getRandColor(160, 200)); // 随机产生165条干扰线，使图象中的认证码不易被其它程序探测到
        for (int i = 0; i < 165; i++) {  
            int x = random.nextInt(WIDTH);  
            int y = random.nextInt(HEIGHT);  
            int xl = random.nextInt(12);  
            int yl = random.nextInt(12);  
            g.drawLine(x, y, x + xl, y + yl);  
        }  
        return g;  
    }  
  
    /*
    public ByteArrayInputStream drawImage(BufferedImage image) {  
        ByteArrayInputStream input = null;  
        ByteArrayOutputStream output = new ByteArrayOutputStream();  
        try {  
            ImageOutputStream imageOut = ImageIO  
                    .createImageOutputStream(output);  
            ImageIO.write(image, "JPEG", imageOut);  
            imageOut.close();  
            input = new ByteArrayInputStream(output.toByteArray());  
        } catch (Exception e) {  
            System.out.println("验证码图片产生出现错误：" + e.toString());  
        }  
        return input;  
    }  
    */
  
    /* 
     * 功能：给定范围获得随机颜色 
     */  
    private Color getRandColor(int fc, int bc) {  
        Random random = new Random();  
        if (fc > 255)  
            fc = 255;  
        if (bc > 255)  
            bc = 255;  
        int r = fc + random.nextInt(bc - fc);  
        int g = fc + random.nextInt(bc - fc);  
        int b = fc + random.nextInt(bc - fc);  
        return new Color(r, g, b);  
    }  
  
    /** 
     * 功能：获取验证码的字符串值 
     *  
     * @return 
     */  
    public String getVerificationCodeValue() {  
        return this.getStr();  
    }  
  
    /** 
     * 功能：取得验证码图片 
     *  
     * @return 
     */  
    public BufferedImage getImage() {  
        return this.image;  
    }  
  
    public String getStr() {  
        return str;  
    }  
  
    public void setStr(String str) {  
        this.str = str;  
    }  
  
    public void setImage(BufferedImage image) {  
        this.image = image;  
    }  
}
