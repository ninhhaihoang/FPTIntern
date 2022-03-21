package com.example.fptintern.Services;

import com.example.fptintern.Models.Order;

import java.util.List;

public interface OrderService {
    Order getOrder(Long id);
    List<Order> searchOrders(String s);
    List<Order> getOrders();
    void saveOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(Long id);
    Long total(Long productId, Integer number);
}
