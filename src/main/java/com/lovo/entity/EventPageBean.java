package com.lovo.entity;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public class EventPageBean<T> {
    private int currPage;
    private int totalPage;
    private List<T>  list;

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public EventPageBean() {
    }

    public EventPageBean(int currPage, int totalPage, List<T> list) {
        this.currPage = currPage;
        this.totalPage = totalPage;
        this.list = list;
    }
}
