package com.webecommerce.dao;

import com.webecommerce.dao.impl.AbstractDAO;

import javax.persistence.EntityManager;
import java.util.List;

public interface GenericDAO <T> {
    // Lấy đối tượng qua ID
    T findById(Long id);

    // Lấy tất cả các đối tượng
    List<T> findAll();

    // Thêm đối tượng mới
    T insert(T entity);

    // Cập nhật đối tượng
    T update(T entity);

    // Xóa đối tượng
    boolean delete(Long id);
}

