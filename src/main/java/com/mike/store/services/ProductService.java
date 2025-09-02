package com.mike.store.services;

import com.mike.store.entities.Product;
import com.mike.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IGeneralService<Product> {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));
    }

    @Override
    public List<Product> getAll() {
        var obj = productRepository.findAll();
        return obj;
    }

    @Override
    public Product insert(Product obj) {
        return productRepository.save(obj);
    }

    @Override
    public Product update(Long id, Product obj) {
        var obj1 = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found! Id: " + id));
       obj1.setName(obj.getName());
       obj1.setPrice(obj.getPrice());
       obj1.setDescription(obj.getDescription());
       obj1.setImgUrl(obj.getImgUrl());
        productRepository.save(obj1);
        return obj1;
    }

    @Override
    public Product delete(Long id) {
        var obj  = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found! Id: " + id));
        productRepository.delete(obj);
        return obj;
    }
}
