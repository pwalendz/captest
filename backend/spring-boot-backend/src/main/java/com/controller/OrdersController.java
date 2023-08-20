package com.controller;

import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.entity.Account;
import com.entity.Orders;
import com.entity.Product;
import com.service.AccountService;
import com.service.OrdersService;
import com.service.ProductService;

@RestController
@RequestMapping("orders")
@CrossOrigin
public class OrdersController {

	@Autowired
	ProductService productService;
	@Autowired
	OrdersService ordersService;
	
	 @Autowired
	 AccountService accountService;
	
	 @PostMapping(value = "placeOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
	 public String placeOrder(@RequestBody List<Orders> ordersList) {
	     List<String> orderResults = new ArrayList<>(); // Create a list to store order results

	     for (Orders order : ordersList) {
	         String email = order.getEmailid();
	         Account account = accountService.findByEmail(email);

	         if (account == null) {
	             return "Account not found for order with email: " + email;
	         }

	         Product product = productService.findById(order.getProductid());
	         if (product == null) {
	             return "Product not found for order with product ID: " + order.getProductid();
	         }

	         if (product.getQty() < 1) {
	             return "Product is out of stock: " + product.getPname();
	         }

	         float orderTotal = product.getPrice();
	         if (account.getAmount() < orderTotal) {
	             return "Insufficient funds in the account for order with email: " + email;
	         }

	         account.setAmount(account.getAmount() - orderTotal);
	         order.setAccount(account);
	         order.setOrderplaced(LocalDate.now());
	         order.setProduct(product);

	         String result = ordersService.placeOrder(order);
	         productService.decrementQty(product.getPid());

	         // Add the order result to the list
	         orderResults.add("Order placed successfully for product: " + product.getPname() +
	                          " by account: " + account.getEmail() +
	                          ". Remaining balance: " + account.getAmount());
	     }

	     // Build the summary message based on the collected results
	     StringBuilder summary = new StringBuilder("Order Summary:\n");
	     for (String result : orderResults) {
	         summary.append(result).append("\n");
	     }

	     return summary.toString(); // Return the summary message
	 }
	 
	 @PostMapping(value = "remainingBalance", consumes = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Float> getRemainingBalance(@RequestBody Map<String, String> requestBody) {
	     String email = requestBody.get("email");
	     Account account = accountService.findByEmail(email);
	     if (account != null) {
	         return ResponseEntity.ok(account.getAmount());
	     } else {
	         return ResponseEntity.notFound().build();
	     }
	 }

	
	 @GetMapping(value = "viewOrderDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	 	public List<Orders> viewOrderDetails() {
	        return ordersService.viewAllOrderDetails();
	    }
	 
	 @PostMapping(value = "viewOrderDetails2", produces = MediaType.APPLICATION_JSON_VALUE)
	 public List<Orders> viewOrderDetails(@RequestBody Map<String, String> requestBody) {
	     String email = requestBody.get("email");
	     return ordersService.viewOrderDetailsByEmail(email);
	 }

}
