package com.mrc.Ecommers.controller;

import com.mrc.Ecommers.dto.OrderDto;
import com.mrc.Ecommers.entity.Order;

import com.mrc.Ecommers.repository.OrderRepository;
import com.mrc.Ecommers.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderService service;

    @PostMapping("/checkout")
    public String order(@RequestBody OrderDto order){
         return "Order successfully placed "+service.save(order);
    }
}
