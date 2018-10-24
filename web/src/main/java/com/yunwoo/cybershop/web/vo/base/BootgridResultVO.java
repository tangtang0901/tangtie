package com.yunwoo.cybershop.web.vo.base;

import java.util.List;

/**
 * Created by Fox on 2017/6/11.
 */
public class BootgridResultVO {
    private List rows;
    private Integer total;

    public BootgridResultVO(){

    }
    public BootgridResultVO( List rows, Integer total){
        this.rows = rows;
        this.total = total;
    }



    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
