package com.niiran.engineering.service;

import com.niiran.engineering.dto.ProductRequest;
import com.niiran.engineering.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductRequest product);
    Product updateProduct(Long id, ProductRequest product);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Long countProducts();
}
