package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Product;
import com.service.ProductService;

@RestController
@RequestMapping("product")
@CrossOrigin
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "storeProductInfo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String storeProductData(Model model, @RequestBody Product product) {
        String result = productService.storeProduct(product);
        model.addAttribute("product", product);
        model.addAttribute("msg", result);
        System.out.println(product);
        return productService.storeProduct(product);
    }
    
    @PostMapping(value = "updateProductInfo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateProductData(Model model, @RequestBody Product product) {
        String result = productService.updateProduct(product); // Implement this method in ProductService
        model.addAttribute("product", product);
        model.addAttribute("msg", result);
        System.out.println(product);
        return productService.updateProduct(product);
    }
    
    @PostMapping(value = "deleteProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String deleteProduct(@RequestBody Product product) {
        String result = productService.deleteProduct(product.getPid());
        return result;
    }

    @GetMapping(value = "viewProductDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> viewProducts() {
        List<Product> listOfProducts = productService.findAllProducts();
        return ResponseEntity.ok(listOfProducts);
    }
}
