package com.example.fptintern.Services;

import com.example.fptintern.Models.Customer;
import com.example.fptintern.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepo;

    @Override
    public Customer getCustomer(Long id) {
        return customerRepo.findById(id).get();
    }

    @Override
    public Customer getCustomerCode(String code) {
        return customerRepo.findCustomerByCustomerCode(code);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public List<Customer> searchCustomers(String s) {
        return customerRepo.searchCustomer(s);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepo.deleteById(id);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepo.save(customer);
    }
}
