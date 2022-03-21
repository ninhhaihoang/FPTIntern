package com.example.fptintern.Controllers;

import com.example.fptintern.Models.Product;
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
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping(value = "/list-products")
    public String getProducts (@Param("keyword") String keyword, Model model) {
        try{
        List<Product> products = productService.getProducts();
        model.addAttribute("keyword", keyword);
        model.addAttribute("product", products);
        return "products";}
        catch (Exception e) {
        System.out.println(e);
        return "404";
    }
    }

    @GetMapping(value = "/add-product")
    public String addProduct (Model model) {
        try{
        model.addAttribute("product", new Product());
        return "prod-form";}
        catch (Exception e) {
        System.out.println(e);
        return "404";
    }
    }

    @PostMapping(value = "/process-save-product")
    public String processSaveProduct (@ModelAttribute(name = "product") Product product) {
        try{
        productService.saveProduct(product);
        return "redirect:/list-products";}
        catch (Exception e) {
        System.out.println(e);
        return "404";
    }
    }

    @GetMapping(value = "/product/delete/{productId}")
    public String deleteProduct (@PathVariable(name = "productId") Long productId){
        try{
        productService.deleteProduct(productId);
        return "redirect:/list-products";}
        catch (Exception e) {
            System.out.println(e);
            return "404";
        }
    }

    @GetMapping(value = "/product/search")
    public String searchProduct (@Param("keyword") String keyword, Model model) {
        try{
        List<Product> products = productService.searchProducts(keyword);
        System.out.println("keyword:" + keyword);
        model.addAttribute("product", products);
        return "products";}
        catch (Exception e) {
            System.out.println(e);
            return "404";
        }
    }

    @GetMapping(value = "/product-update/{productId}")
    public String updatePage (@PathVariable(name = "productId") Long productId, Model model) {
        try {
        Product product = productService.getProduct(productId);
        model.addAttribute("product", product);
        return "prod-form";}
        catch (Exception e) {
            System.out.println(e);
            return "404";
        }
    }
}
