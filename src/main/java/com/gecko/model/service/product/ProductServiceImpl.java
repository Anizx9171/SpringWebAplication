package com.gecko.model.service.product;

import com.gecko.model.dao.product.ProductDAO;
import com.gecko.model.entity.Category;
import com.gecko.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductDAO productDAO;
    @Override
    public List<Product> findAll(Integer integer) {
        return productDAO.findAll(integer);
    }

    @Override
    public boolean create(Product product) {
        return productDAO.create(product);
    }

    @Override
    public boolean update(Product product) {
        return productDAO.update(product);
    }

    @Override
    public boolean delete(Integer integer) {
        return productDAO.delete(integer);
    }

    @Override
    public Product findById(Integer integer) {
        return productDAO.findById(integer);
    }

    @Override
    public List<Product> findByName(String str) {
        return productDAO.findByName(str);
    }

    @Override
    public int getTotalPage() {
        return productDAO.getTotalPage();
    }

    @Override
    public List<Product> getAllProduct() {
        return productDAO.getAllProduct();
    }

    @Override
    public List<Product> findByCategory(Integer id) {
        return productDAO.findByCategory(id);
    }
}
