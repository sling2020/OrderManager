package com.api.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.Order;
import com.api.model.OrderStatus;
import com.api.model.StandarResponse;
import com.api.services.OrderManagerService;

@RequestMapping("/api/orders")
@RestController
public class OrderManagerController {
	@Autowired
	OrderManagerService orderManagerService;
	
	@PostMapping("/create")
	public ResponseEntity<StandarResponse> createOrder(@RequestBody Order order){
		return ResponseEntity.ok(orderManagerService.createOrder(order));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StandarResponse> findOrderById(@PathVariable long id){
		return ResponseEntity.ok(orderManagerService.findOrderById(id));
	}
	 
	@GetMapping("/status/{status}")
	public ResponseEntity<StandarResponse> findOrderByStatus(@PathVariable String status){
		return ResponseEntity.ok(orderManagerService.findOrderByStatus(status));
	}
	
	@GetMapping("/dates")
	public ResponseEntity<StandarResponse> findOrderCreateDateBetween(@RequestParam Date startDate, Date endDate){
		return ResponseEntity.ok(orderManagerService.findOrderCreateDateBetween(startDate, endDate));
	}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<StandarResponse> updateOrderStatus(@PathVariable long id, @RequestParam OrderStatus newStatus){
		return ResponseEntity.ok(orderManagerService.updateOrderStatus(id, newStatus));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<StandarResponse> cancelOrderById(@PathVariable long id){
		return ResponseEntity.ok(orderManagerService.cancelOrderById(id));
	}
	
}
