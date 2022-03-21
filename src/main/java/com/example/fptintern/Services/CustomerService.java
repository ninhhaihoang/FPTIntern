package com.example.fptintern.Services;

import com.example.fptintern.Models.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomer(Long id);
    Customer getCustomerCode(String code);
    List<Customer> getCustomers();
    List<Customer> searchCustomers(String s);
    void deleteCustomer(Long id);
    void saveCustomer(Customer customer);
}
