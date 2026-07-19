package com.niiran.engineering.tools;

import com.niiran.engineering.dto.ProductRequest;
import com.niiran.engineering.entity.Product;
import com.niiran.engineering.service.ProductService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductTool {

    private final ProductService productService;
    public ProductTool(ProductService productService) {
        this.productService = productService;
    }

    @Tool(description = "Create a new product")
    public Product createProduct(ProductRequest request) {
        System.out.println("PRODUCT CREATION");
        return productService.createProduct(request);
    }

    @Tool(description = "Update an existing product")
    public Product updateProduct(Long id, ProductRequest request) {
        return productService.updateProduct(id, request);
    }

    @Tool(description = "Delete a product")
    public String deleteProduct(Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully.";
    }

    @Tool(description = "Find product by id")
    public Product getProductById(Long id) {
        return productService.getProductById(id);
    }

    @Tool(description = "List all products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @Tool(description = "Count total products")
    public Long countProducts() {
        return productService.countProducts();
    }
}
