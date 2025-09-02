package com.mike.store.resources;

import com.mike.store.entities.Category;
import com.mike.store.entities.DTO.CategoryDTO;
import com.mike.store.entities.DTO.ProductDTO;
import com.mike.store.entities.Product;
import com.mike.store.repository.ProductRepository;
import com.mike.store.services.ProductService;
import jakarta.persistence.Table;
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
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll()
    {
        try {
            List<Product> products = productService.getAll();
            List<ProductDTO> productDTOS = new ArrayList<>();
            for (Product p : products) {
                ProductDTO pDTO = new ProductDTO();
                pDTO.setId(p.getId());
                pDTO.setName(p.getName());
                pDTO.setPrice(p.getPrice());
                pDTO.setDescription(p.getDescription());
                pDTO.setImgUrl(p.getImgUrl());
                pDTO.setCategory(p.getCategory());
                productDTOS.add(pDTO);
            }
            return ResponseEntity.ok().body(productDTOS);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id)
    {
        ProductDTO productDTO = null;
        try {
            var exists = productService.getById(id);
            if (exists != null) {
                productDTO = new ProductDTO(
                        exists.getId(),
                        exists.getName(),
                        exists.getDescription(),
                        exists.getPrice(),
                        exists.getImgUrl()
                );
                productDTO.setCategory(exists.getCategory());
            }

        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(productDTO);
    }

}
