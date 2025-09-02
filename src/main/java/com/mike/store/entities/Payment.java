package com.mike.store.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Order order;

    public Payment() {
    }

    public Payment(Long id, Instant moment) {
        this.id = id;
        this.moment = moment;
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

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "Payment [id=" + id + ", moment=" + moment + ", order=" + order + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        if (id != null ? !id.equals(payment.id) : payment.id != null) return false;
        if (moment != null ? !moment.equals(payment.moment) : payment.moment != null) return false;
        return order != null ? order.equals(payment.order) : payment.order == null;
    }

}
