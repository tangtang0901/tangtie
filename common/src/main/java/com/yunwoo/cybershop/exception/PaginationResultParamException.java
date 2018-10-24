package com.yunwoo.cybershop.exception;


/**
 * 分页类参数异常
 */
public class PaginationResultParamException extends BaseException {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    public PaginationResultParamException() {
        super();
    }

    public PaginationResultParamException(String message){
        super(message);
    }
}
