package com.mike.store.services;

import com.mike.store.entities.Category;
import com.mike.store.entities.OrderStatus;
import com.mike.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements IGeneralService<Category> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found! Id: " + id));
    }

    @Override
    public List<Category> getAll() {
        var obj = categoryRepository.findAll();
        return obj;
    }

    @Override
    public Category insert(Category obj) {
        return categoryRepository.save(obj);
    }

    @Override
    public Category update(Long id, Category obj) {
        var obj1 = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found! Id: " + id));
        //obj1.setId(obj.getId());
        obj1.setName(obj.getName());
        categoryRepository.save(obj1);
        return obj1;
    }

    @Override
    public Category delete(Long id) {
        var obj  = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found! Id: " + id));
        categoryRepository.delete(obj);
        return obj;
    }
}
