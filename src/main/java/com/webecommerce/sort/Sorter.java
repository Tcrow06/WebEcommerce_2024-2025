package com.webecommerce.sort;

public class Sorter {
    private String sortBy;

    public Sorter(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
