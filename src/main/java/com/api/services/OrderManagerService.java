package com.api.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.model.Order;
import com.api.model.OrderStatus;
import com.api.model.StandarResponse;
import com.api.repository.OrderRepository;

@Service
public class OrderManagerService {
	@Autowired
	OrderRepository OrderRepository;
	
	public StandarResponse createOrder(Order order) {

		StandarResponse response = 	null;
		try {
			OrderRepository.save(order);
			response = new StandarResponse(true, "Order has been created", order);
		} catch (Exception e) {
			response = new StandarResponse(false, "Error creating a order: " + e.getMessage(), order);
		}		
		return response;
	}
	public StandarResponse findOrderById(long id) {
		StandarResponse response = 	null;
		try {
			Optional<Order> result = OrderRepository.findById(id);
			
			response = new StandarResponse(true, "Order found", result.get());
		} catch (Exception e) {
			response = new StandarResponse(false, "Error looking order with id [" + id + "]: " + e.getMessage(), null);
		}		
		return response;
	}	
	public StandarResponse findOrderByStatus(String status) {
		StandarResponse response = 	null;
		try {
			List<Order> result = OrderRepository.findByStatus(status);
			
			response = new StandarResponse(true, "Orders found", result);
		} catch (Exception e) {
			response = new StandarResponse(false, "Error looking order with status [" + status + "]: " + e.getMessage(), null);
		}		
		return response;
	}
	public StandarResponse findOrderCreateDateBetween(Date startDate, Date endDate) {
		StandarResponse response = 	null;
		try {
			List<Order> result = OrderRepository.findByCreationDateBetween(startDate, endDate);
			
			response = new StandarResponse(true, "Orders found", result);
		} catch (Exception e) {
			response = new StandarResponse(false, "Error looking order with creation date between [" + startDate.toGMTString() + "-"+ endDate.toGMTString() + "]: "
					+ e.getMessage(), null);
		}		
		return response;
	}
	public StandarResponse updateOrderStatus(long id, OrderStatus newStatus) {
		StandarResponse response = 	null;
		try {
			Optional<Order> result = OrderRepository.findById(id);
			if (result.isPresent()) {
				result.get().setStatus(newStatus);
				OrderRepository.save(result.get());
				response = new StandarResponse(true, "Order status has been updated", result);
	        } else {
	        	response = new StandarResponse(false, "Order not found id: [" + id + "]", result);
	        }
			
		} catch (Exception e) {
			response = new StandarResponse(false, "Error updating order status with id [" + id +  "]: " +  e.getMessage(), null);
		}
		return response;
	}
	public StandarResponse cancelOrderById(long id) {
		StandarResponse response = 	null;
		try {
			Optional<Order> result = OrderRepository.findById(id);
			if (result.isPresent()) {
				result.get().setStatus(OrderStatus.CANCELLED);
				OrderRepository.save(result.get());
				response = new StandarResponse(true, "Order status has been updated", result);
	        } else {
	        	response = new StandarResponse(false, "Order not found id: [" + id + "]", result);
	        }
			
		} catch (Exception e) {
			response = new StandarResponse(false, "Error updating order status with id [" + id +  "]: " +  e.getMessage(), null);
		}
		return response;
	}

	
	
}
