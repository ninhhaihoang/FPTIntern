package com.example.fptintern.Services;

import com.example.fptintern.Models.Product;
import com.example.fptintern.Repositories.OrderRepository;
import com.example.fptintern.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepo;

    @Autowired
    OrderRepository orderRepo;

    @Override
    public Product getProduct(Long id) {
        return productRepo.findById(id).get();
    }

    @Override
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> searchProducts(String s) {
        return productRepo.searchProducts(s);
    }

    @Override
    public void deleteProduct(Long id) {
        if(orderRepo.findByProduct_ProductId(id).isEmpty()){
            productRepo.deleteById(id);
        } else {
            productRepo.setForeignKeyCheckOff();
            productRepo.deleteProductRelative(id);
            productRepo.setForeignKeyCheckOn();
        }

    }

    @Override
    public void saveProduct(Product product) {
        productRepo.save(product);
    }

}
