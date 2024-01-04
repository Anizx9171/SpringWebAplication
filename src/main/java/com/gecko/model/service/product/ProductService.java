package com.gecko.model.service.product;

import com.gecko.model.entity.Category;
import com.gecko.model.entity.Product;
import com.gecko.model.service.IGenericService;

import java.util.List;

public interface ProductService extends IGenericService<Product, Integer> {
    int getTotalPage();
    List<Product> findByCategory(Integer id);
    List<Product> getAllProduct();
}
