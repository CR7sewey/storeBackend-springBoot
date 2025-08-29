package com.mike.store.entities.DTO;

import com.mike.store.entities.OrderStatus;
import com.mike.store.entities.User;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Instant moment;
    private Integer status;
    private UserDTO client;

    public OrderDTO() {
    }

    public OrderDTO(Long id, Instant moment, OrderStatus status, UserDTO client) {
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


    public UserDTO getClient() {
        return client;
    }
    public void setClient(UserDTO user) {
        this.client = user;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.status = orderStatus.getValue();
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        return this.id.equals(((OrderDTO) obj).id);
    }

    @Override
    public String toString() {
        return "Order: " + this.id + ", moment: " + this.moment +  ", status: " + this.status + ", user: " + this.client;
    }
}
