package com.mike.store.entities.pk;

import com.mike.store.entities.Order;
import com.mike.store.entities.OrderItem;
import com.mike.store.entities.Product;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

// class to generate a compound id for the intermediate column

@Embeddable
public class OrderItemPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    public Order getOrder() {
        return orderId;
    }

    public void setOrder(Order order) {
        this.orderId = order;
    }

    public Product getProduct() {
        return productId;
    }

    public void setProduct(Product product) {
        this.productId = product;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderItemPK other = (OrderItemPK) obj;
        return Objects.equals(orderId, other.orderId) && Objects.equals(productId, other.productId);
    }


}
