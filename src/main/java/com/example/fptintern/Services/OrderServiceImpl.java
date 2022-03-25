package com.example.fptintern.Services;

import com.example.fptintern.Models.Order;
import com.example.fptintern.Models.Product;
import com.example.fptintern.Repositories.OrderRepository;
import com.example.fptintern.Repositories.ProductRepository;
import com.example.fptintern.Repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepo;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    StatusRepository statusRepo;

    @Override
    public Order getOrder(Long id) {
        return orderRepo.findById(id).get();
    }

    @Override
    public List<Order> getOrdersByCustomer(Long id) {
        return orderRepo.findByCustomer_CustomerId(id);
    }

    @Override
    public List<Order> searchOrders(String s) {
        return orderRepo.searchOrder(s);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepo.findAll();
    }

    @Override
    public void saveOrder(Order order) {
        order.setStatus(statusRepo.findById(1L).get());
        orderRepo.save(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepo.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }

    @Override
    public Long total(Long productId, Integer number) {
        Product product = productRepo.findById(productId).get();
        return product.getPrice()*number;
    }
}
