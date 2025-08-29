package com.mike.store.entities;

public enum OrderStatus {
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int value;

    OrderStatus(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static OrderStatus valueOf(int code) {
        for (OrderStatus value: OrderStatus.values()) {
            if (value.getValue() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code!");
    }

}
