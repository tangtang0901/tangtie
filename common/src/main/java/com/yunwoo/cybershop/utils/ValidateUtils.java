package com.yunwoo.cybershop.utils;

import com.yunwoo.cybershop.model.ValidResult;
import com.yunwoo.cybershop.model.ValidResult;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

/**
 * 校验参数工具
 * Created by Fox on 2017/4/15.
 */
public final class ValidateUtils {

    private static javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> ValidResult validate(T entity){
        ValidResult validResult = new ValidResult();
//        validResult.setValid(true);

        if(entity != null){
            Set<ConstraintViolation<T>> result = validator.validate(entity);
            if(CollectionUtils.isNotEmpty(result)) {
                validResult.setValid(false);
                validResult.setMessage(result.iterator().next().getMessage());
            }else {
                validResult.setValid(true);
            }
        }else {
            validResult.setValid(false);
            validResult.setMessage("参数为空");
        }
        return validResult;
    }
}
