package com.mike.store.entities;

import java.util.Date;

public class Order {

    private Integer id;
    private Date moment;
    private OrderStatus status;
    private User user;

    public Order(Integer id, Date moment, OrderStatus status, User user) {
        super();
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getMoment() {
        return moment;
    }
    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }





    // mEthods

    public double total() {
        return 0.0;
    }




    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", moment=" + moment + '}';

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
