package com.gecko.model.service.category;

import com.gecko.model.dao.category.CategoryDAO;
import com.gecko.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public List<Category> getAllCategory() {
        return categoryDAO.getAllCategory();
    }

    @Override
    public List<Category> findAll(Integer noPage) {
        return categoryDAO.findAll(noPage);
    }

    @Override
    public boolean create(Category category) {
        return categoryDAO.create(category);
    }

    @Override
    public boolean update(Category category) {
        return categoryDAO.update(category);
    }

    @Override
    public boolean delete(Integer integer) {
        return categoryDAO.delete(integer);
    }

    @Override
    public Category findById(Integer integer) {
        return categoryDAO.findById(integer);
    }

    @Override
    public List<Category> findByName(String str) {
        return categoryDAO.findByName(str);
    }

    @Override
    public  int getTotalPage() {
        return categoryDAO.getTotalPage();
    }
}
