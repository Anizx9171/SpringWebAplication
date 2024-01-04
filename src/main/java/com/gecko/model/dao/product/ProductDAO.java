package com.gecko.model.dao.product;

import com.gecko.model.dao.IGenericDAO;
import com.gecko.model.entity.Category;
import com.gecko.model.entity.Product;

import java.util.List;

public interface ProductDAO extends IGenericDAO<Product, Integer> {
    int getTotalPage();
    List<Product> findByCategory(Integer id);
    List<Product> getAllProduct();
}
