package com.mike.store.entities.DTO;

import com.mike.store.entities.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// tb_user
public class UserDTO implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String phone;

    // One to Many
    private List<OrderDTO> orders = new ArrayList<>();

    public UserDTO(Long id, String name, String email, String phone) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }
    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        return this.email.equals(((UserDTO) obj).email);
    }

    @Override
    public String toString() {
        return "User: " + this.name + ", email: " + this.email;
    }

}
