package com.yunwoo.cybershop.web.vo.base;

import java.util.List;

/**
 * 疾病目标 VO
 * Created by Fox on 2017/5/20.
 */
public class DiseaseVO {
    private Integer id;
    private Integer type;
    private String name;
    private String avoidance;
    private String fit;
    private List<Integer> avoidanceIds;
    private List<Integer> fitIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvoidance() {
        return avoidance;
    }

    public void setAvoidance(String avoidance) {
        this.avoidance = avoidance;
    }

    public String getFit() {
        return fit;
    }

    public void setFit(String fit) {
        this.fit = fit;
    }

    public List<Integer> getAvoidanceIds() {
        return avoidanceIds;
    }

    public void setAvoidanceIds(List<Integer> avoidanceIds) {
        this.avoidanceIds = avoidanceIds;
    }

    public List<Integer> getFitIds() {
        return fitIds;
    }

    public void setFitIds(List<Integer> fitIds) {
        this.fitIds = fitIds;
    }
}
