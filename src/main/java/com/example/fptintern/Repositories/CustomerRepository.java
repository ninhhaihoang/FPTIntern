package com.example.fptintern.Repositories;

import com.example.fptintern.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByCustomerCode(String customerCode);

    @Query("SELECT c FROM Customer c WHERE c.fullName LIKE %?1% OR c.customerCode LIKE %?1%")
    List<Customer> searchCustomer(String keyword);
}
