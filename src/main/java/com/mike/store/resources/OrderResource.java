package com.mike.store.resources;

import com.mike.store.entities.DTO.OrderDTO;
import com.mike.store.entities.DTO.UserDTO;
import com.mike.store.entities.Order;
import com.mike.store.entities.OrderStatus;
import com.mike.store.entities.User;
import com.mike.store.services.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAll()
    {
        var randomOrders = new ArrayList<OrderDTO>();
        //
        var rO = new ArrayList<Order>();
        try {
            List<Order> orders = orderService.getAll();
            rO.addAll(orders);
            System.out.println(orders);
            orders.forEach(order -> {
                    //System.out.println(OrderStatus.valueOf(order.getStatus().toString()));
                    randomOrders.add(
                        new OrderDTO(
                                order.getId(),
                                order.getMoment(),
                                OrderStatus.valueOf(order.getStatus()),
                                new UserDTO(
                                        order.getClient().getId(),
                                        order.getClient().getName(),
                                        order.getClient().getEmail(),
                                        order.getClient().getPhone()
                                )
                        ));
                    }
            );
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(rO);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id)
    {
        Order randomOrders = null;

        try {

            var exists = orderService.getById(id);
            randomOrders = exists;
            /*
            if (exists != null) {
                randomOrders = new OrderDTO(
                        exists.getId(),
                        exists.getMoment(),
                        OrderStatus.valueOf(exists.getStatus()),
                        new UserDTO(
                                exists.getClient().getId(),
                                exists.getClient().getName(),
                                exists.getClient().getEmail(),
                                exists.getClient().getPhone()
                        )
                );
            }*/
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(randomOrders);
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order)
    {
        try {
            /*
            var randomOrder = new OrderDTO(
                    order.getId(),
                    order.getMoment(),
                    OrderStatus.valueOf(order.getStatus()),
                    new UserDTO(
                            order.getClient().getId(),
                            order.getClient().getName(),
                            order.getClient().getEmail(),
                            order.getClient().getPhone()
                    )
            );*/
            orderService.insert(order);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
            return ResponseEntity.created(uri).body(order);

        }
        catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order order)
    {
        OrderDTO randomOrder = null;
        try {
            var exists = orderService.update(id, order);
            if (exists != null) {
                randomOrder = new OrderDTO(
                        order.getId(),
                        order.getMoment(),
                        OrderStatus.valueOf(order.getStatus()),
                        new UserDTO(
                                order.getClient().getId(),
                                order.getClient().getName(),
                                order.getClient().getEmail(),
                                order.getClient().getPhone()
                        )
                );
            }
            return  ResponseEntity.ok().body(order);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Order> delete(@PathVariable Long id)
    {
        try {
            orderService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



}
