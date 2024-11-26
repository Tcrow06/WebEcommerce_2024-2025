package com.webecommerce.dto;

import java.util.List;

public class BaseDTO <T> {
    private Long id;
    private Long [] ids;
    private String realPathFile;
    private int page;
    private Integer maxPageItem;

    public BaseDTO(Long id) {
        this.id = id;
    }

    public BaseDTO() {
    }

    private int totalPage;
    private Long totalItem;
    private String sortBy;

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Integer getMaxPageItem() {
        return maxPageItem;
    }

    public void setMaxPageItem(Integer maxPageItem) {
        this.maxPageItem = maxPageItem;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Long totalItem) {
        this.totalItem = totalItem;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    private List <T> resultList ;

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealPathFile() {
        return realPathFile;
    }

    public void setRealPathFile(String realPathFile) {
        this.realPathFile = realPathFile;
    }
}
