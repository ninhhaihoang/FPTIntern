package com.example.fptintern.Controllers;

import com.example.fptintern.Models.Customer;
import com.example.fptintern.Models.Order;
import com.example.fptintern.Models.Product;
import com.example.fptintern.Models.Status;
import com.example.fptintern.Repositories.StatusRepository;
import com.example.fptintern.Services.CustomerServiceImpl;
import com.example.fptintern.Services.OrderServiceImpl;
import com.example.fptintern.Services.ProductServiceImpl;
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
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    StatusRepository statusRepo;

    @GetMapping(value = "/list-orders")
    public String listOrders(Model model) {
        try{
        List<Order> orders = orderService.getOrders();
        model.addAttribute("order", orders);
        return "Order";}
        catch (Exception e) {
        System.out.println(e);
        return "404";
    }
    }

    @GetMapping(value = "/add-orders/{productId}")
    public String addOrder(Model model, @PathVariable(name = "productId") Long productId){
        try{
        Product product = productService.getProduct(productId);
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("cust", customers);
        model.addAttribute("prod", product);
        System.out.println("this is product: " + product);
        model.addAttribute("order", new Order());
        return "order-form";
    }
        catch (Exception e) {
        System.out.println(e);
        return "404";
    }
    }

    @GetMapping(value = "/order/search")
    public String searchOrder(@Param(value = "keyword") String keyword, Model model) {
        try{
        List<Order> orders = orderService.searchOrders(keyword);
        model.addAttribute("order", orders);
        return "Order";}
        catch (Exception e) {
        System.out.println(e);
        return "404";
    }
    }

    @PostMapping(value = "/add-order-process")
    public String addOrder (@ModelAttribute(name = "order") Order order) {
        try{
        System.out.println("this is new order: " + order);
        orderService.saveOrder(order);
        return "redirect:/list-orders";
    }
        catch (Exception e) {
        System.out.println(e);
        return "404";
    }
    }

    @GetMapping(value = "/delete-order/{orderId}")
    public String deleteOrder (@PathVariable(name = "orderId") Long orderId) {
        try{
        orderService.deleteOrder(orderId);
        return "redirect:/list-orders";
    }
        catch (Exception e) {
        System.out.println(e);
        return "404";
    }
    }

    @GetMapping(value = "/update-order/{orderId}")
    public String updatePage (@PathVariable(name = "orderId") Long orderId, Model model) {
        try{
        Order order = orderService.getOrder(orderId);
        List<Status> statuses = statusRepo.findAll();
        model.addAttribute("statuses", statuses);
        model.addAttribute("order", order);
        return "order-update";
    }
        catch (Exception e) {
        System.out.println(e);
        return "404";
    }
    }

    @PostMapping(value = "/update-order-process")
    public String updateOrder (@ModelAttribute(name = "order") Order order) {
        try{
        System.out.println("this is the update order: " + order);
        orderService.updateOrder(order);
        return "redirect:/list-orders";
    }
        catch (Exception e) {
        System.out.println(e);
        return "404";
    }
    }
}
