package com.apifull.controller;

import com.apifull.model.Products;
import com.apifull.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Products> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Products getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    // 🔒 luego se protege para ADMIN
    @PostMapping
    public Products create(@RequestBody Products product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
