package com.mike.store.resources;

import com.mike.store.entities.Category;
import com.mike.store.entities.DTO.CategoryDTO;
import com.mike.store.entities.DTO.UserDTO;
import com.mike.store.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    CategoryService categoryService;


    @GetMapping
    public ResponseEntity<List<Category>> getAll()
    {
        try {
            List<Category> categories = categoryService.getAll();
            List<CategoryDTO> categoryDTOS = new ArrayList<>();
            /*for (Category category : categories) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setId(category.getId());
                categoryDTO.setName(category.getName());
                categoryDTOS.add(categoryDTO);
            }*/
            return ResponseEntity.ok().body(categories);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id)
    {
        Category categoryDTO = null;
        try {
            var exists = categoryService.getById(id);
            /*if (exists != null) {
                categoryDTO = new CategoryDTO(
                        exists.getId(),
                        exists.getName()
                );
            }*/
            categoryDTO = exists;

        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(categoryDTO);
    }



}
