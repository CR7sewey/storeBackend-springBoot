package com.mike.store.services;

import com.mike.store.entities.DTO.UserDTO;
import com.mike.store.entities.User;
import com.mike.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IGeneralService<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getById(Long id) {

        var obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("User not found! Id: " + id));

    }

    @Override
    public List<User> getAll() {
        List<User> obj = userRepository.findAll();
        return obj;

    }

    @Override
    public User insert(User obj) {
        return userRepository.save(obj);
    }

    @Override
    public User update(Long id, User obj) {
        var obj1 = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found! Id: " + id));
        obj1.setName(obj.getName());
        obj1.setEmail(obj.getEmail());
        obj1.setPhone(obj.getPhone());
        obj1.setPassword(obj.getPassword());
        userRepository.save(obj1);
        return obj1;
    }

    @Override
    public User delete(Long id) {
        var obj  = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found! Id: " + id));
        userRepository.delete(obj);
        return obj;
    }
}
