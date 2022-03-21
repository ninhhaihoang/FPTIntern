package com.example.fptintern.Controllers;

import com.example.fptintern.Models.Customer;
import com.example.fptintern.Services.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    CustomerServiceImpl customerService;

    @GetMapping(value = {"/list-customer", "/"})
    public String getCustomers (Model model) {
        try{
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customer", customers);
        return "index";}
        catch (Exception e) {
        System.out.println(e);
        return "404";
    }
    }

    @GetMapping(value = "/customer/search")
    public String searchCustomers (@Param(value = "keyword") String keyword, Model model) {
        try{
        List<Customer> customers = customerService.searchCustomers(keyword);
        model.addAttribute("customer", customers);
        return "index";}
        catch (Exception e) {
        System.out.println(e);
        return "404";
    }
    }

    @GetMapping(value = "/add-customer")
    public String addCustomer (Model model) {
        try{
        model.addAttribute("customer", new Customer());
        return "cust-form";}
        catch (Exception e) {
        System.out.println(e);
        return "404";
    }
    }

    @PostMapping(value = "/process-save-cust")
    public String processSaveCust (@ModelAttribute(name = "customer") Customer customer) {
        try{
        customerService.saveCustomer(customer);
        return "redirect:/list-customer";}
        catch (Exception e) {
        System.out.println(e);
        return "404";
    }
    }

    @GetMapping(value = "/delete/{customerId}")
    public String deleteCustomer(@PathVariable(name = "customerId") Long customerId) {
        try{
        customerService.deleteCustomer(customerId);
        return "redirect:/list-customer";}
        catch (Exception e) {
        System.out.println(e);
        return "404";
    }
    }

}
