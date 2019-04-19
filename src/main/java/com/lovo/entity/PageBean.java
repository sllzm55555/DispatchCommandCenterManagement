package com.lovo.entity;

import java.util.List;

public class PageBean<T> {

    private List<T> tableBeans;
    //private List<Object> tableBeans;用object也可以
    private Integer currPate;
    private Integer totalPate;

    public List<T> getTableBeans() {
        return tableBeans;
    }

    public void setTableBeans(List<T> tableBeans) {
        this.tableBeans = tableBeans;
    }

    public Integer getCurrPate() {
        return currPate;
    }

    public void setCurrPate(Integer currPate) {
        this.currPate = currPate;
    }

    public Integer getTotalPate() {
        return totalPate;
    }

    public void setTotalPate(Integer totalPate) {
        this.totalPate = totalPate;
    }
}
