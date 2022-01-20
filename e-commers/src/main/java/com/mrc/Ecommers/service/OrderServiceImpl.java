package com.mrc.Ecommers.service;

import com.mrc.Ecommers.dto.OrderDto;
import com.mrc.Ecommers.entity.Item;
import com.mrc.Ecommers.entity.Order;
import com.mrc.Ecommers.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository
            ;
    @Override
    public String save(OrderDto order) {
        // take all customer data from customer service by using feign cliwnt like addresss custome rdetaul
        Order od = new Order();
        od.setAddress("ap");
        od.setTotalAmount(12.0);
        od.setTrackId(UUID.randomUUID().toString());
        List<Item> items = new ArrayList<>();
        Item it= new Item();
        // product price take form product controlleer by pid
        // get Product(order.getPId())
        it.setPrice(12*order.getQuantity());
        it.setQuantity(order.getQuantity());
        it.setProductId(1);
        items.add(it);
        od.setItems(items);
        return orderRepository.save(od).getTrackId();
    }
}
