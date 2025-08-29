package com.mike.store.services;

import com.mike.store.entities.Order;
import com.mike.store.entities.OrderStatus;
import com.mike.store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IGeneralService<Order> {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order getById(Long id) {
        var obj = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order Not Found"));
        return obj;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order insert(Order obj) {

        return orderRepository.save(obj);
    }

    @Override
    public Order update(Long id, Order obj) {
        var obj1 = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found! Id: " + id));
        //obj1.setId(obj.getId());
        obj1.setMoment(obj.getMoment());
        obj1.setOrderStatus(OrderStatus.valueOf(obj.getStatus()));
        obj1.setClient(obj.getClient());
        orderRepository.save(obj1);
        return obj1;
    }

    @Override
    public Order delete(Long id) {
        var obj  = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found! Id: " + id));
        orderRepository.delete(obj);
        return obj;
    }
}
