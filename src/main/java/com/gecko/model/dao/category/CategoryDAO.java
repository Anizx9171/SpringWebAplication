package com.gecko.model.dao.category;

import com.gecko.model.dao.IGenericDAO;
import com.gecko.model.entity.Category;

import java.util.List;

public interface CategoryDAO extends IGenericDAO<Category, Integer> {
    int getTotalPage();
    List<Category> getAllCategory();
}
