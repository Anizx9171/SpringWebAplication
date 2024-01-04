package com.gecko.model.service.category;

import com.gecko.model.entity.Category;
import com.gecko.model.service.IGenericService;

import java.util.List;

public interface CategoryService extends IGenericService<Category,Integer> {
   int getTotalPage();
    List<Category> getAllCategory();
}
