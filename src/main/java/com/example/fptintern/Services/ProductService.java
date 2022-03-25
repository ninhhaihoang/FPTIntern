package com.example.fptintern.Services;

import com.example.fptintern.Models.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(Long id);
    List<Product> getProducts();
    List<Product> searchProducts(String s);
    void deleteProduct(Long id);
    void saveProduct(Product product);
}
