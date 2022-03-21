package com.example.fptintern.Repositories;

import com.example.fptintern.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.product.productName LIKE %?1% OR o.product.productCode LIKE %?1%" +
            "OR o.customer.fullName LIKE %?1% OR o.customer.customerCode LIKE %?1%")
    List<Order> searchOrder (String s);
}
