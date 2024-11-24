package com.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
  
    List<Order> findByStatus(String estado);    
    List<Order> findByCreationDateBetween(Date startDate, Date endDate);
}
