package com.example.fptintern.Services;

import com.example.fptintern.Models.Customer;
import com.example.fptintern.Repositories.CustomerRepository;
import com.example.fptintern.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    OrderRepository orderRepo;

    @Override
    public Customer getCustomer(Long id) {
        return customerRepo.findById(id).get();
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
        if(orderRepo.findByCustomer_CustomerId(id).isEmpty()){
            customerRepo.deleteById(id);
        } else {
            customerRepo.setForeignKeyCheckOff();
            customerRepo.deleteCustomerRelative(id);
            customerRepo.setForeignKeyCheckOn();
        }
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepo.save(customer);
    }
}
