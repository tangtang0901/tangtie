package com.yunwoo.cybershop.event.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
public class MemberDisableEvent implements Serializable {
    private static final long serialVersionUID = -6449731052912154892L;
    private Integer id;
    /**
     * '最后修改时间'
     */
    private Date lastModifyTime;
}
