package com.yunwoo.cybershop.model;

/**
 * 校验结果
 */
public class ValidResult {

    /**
     * 是否校验通过
     */
    private boolean isValid;

    /**
     * 校验不通过的具体文字信息
     */
    private String message;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
