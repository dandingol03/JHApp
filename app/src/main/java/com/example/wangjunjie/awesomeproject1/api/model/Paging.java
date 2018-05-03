package com.example.wangjunjie.awesomeproject1.api.model;

public class Paging {
    private String order;
    private Integer page;
    private Integer rows;
    private String sort;

    public Paging(){
        order="asc";
        rows=50;
        sort="id";
        page=1;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
