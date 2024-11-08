package com.webecommerce.dto;

import java.util.List;

public class BaseDTO <T> {
    private Long id;

    private Long [] ids;
    private String realPathFile;




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
