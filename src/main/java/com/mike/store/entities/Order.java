package com.mike.store.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mike.store.services.ProductService;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;
    @Column
    private Integer status;

    @JsonIgnore
    @ManyToOne // JPA transfromar em chaves estrangeiras no Banco de dados, relacionamneto entre Order e User
    @JoinColumn(name = "client_id")
    private User client;

    @OneToMany(mappedBy = "id.orderId")
    private Set<OrderItem> orderItems = new HashSet<>();

    public Order() {
    }

    public Order(Long id, Instant moment, OrderStatus status, User client) {
        super();
        this.id = id;
        this.moment = moment;
        this.client = client;
        setOrderStatus(status);
    }



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Instant getMoment() {
        return moment;
    }
    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Integer getStatus() {
        return status;
    }


    public User getClient() {
        return client;
    }
    public void setClient(User user) {
        this.client = user;
    }

    @JsonIgnore
    public Set<Product> getProducts() {
        Set<Product> set = new HashSet<>();
        for (OrderItem i : orderItems) {
            set.add(i.getProduct());
        }
        return set;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.status = orderStatus.getValue();
        }

    }


    // mEthods

    public double total() {
        double total = 0;
        for (OrderItem i : orderItems) {
            total += i.subTotal();
        }
        return total;
    }




    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", moment=" + moment +  ", status=" + status + ", client=" + client + '}';

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (moment != null ? !moment.equals(order.moment) : order.moment != null) return false;
        return true;
    }


}
