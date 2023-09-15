package com.starfall.entity;
//用于管理页数
public class Page {
    int page;
    int lastPage;

    public Page(){

    }
    public Page(int page, int lastPage) {
        this.page = page;
        this.lastPage = lastPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }
}
