package com.mike.store.resources;

import com.mike.store.DTO.UserDTO;
import com.mike.store.entities.User;
import com.mike.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserResource {

    // injecao de dependencia do service
    @Autowired
    private UserRepository userRepository;

    // GET
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO randomUser = null;

        try {
            var exists = userRepository.findById(id).orElse(null);
            if (exists != null) {
                randomUser = new UserDTO(
                        exists.getId(),
                        exists.getName(),
                        exists.getEmail(),
                        exists.getPhone()
                );
            }
            else {
                throw new Exception("User not found");
            }


        }
        catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(randomUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {

        var randomUsers = new ArrayList<UserDTO>();

        try {
            userRepository.findAll().forEach(user -> randomUsers.add(
                    new UserDTO(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            user.getPhone()
                    )
            ));
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(randomUsers);
    }


    // POST
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        try {
            var randomUser = new UserDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPhone()
            );
            userRepository.save(user);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(uri).body(randomUser);

        }
        catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }

    }

    // PUT
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> putUser(@PathVariable Long id, @RequestBody User user) {
        UserDTO randomUser = null;

        try {
            var exists = userRepository.findById(id).orElse(null);
            if (exists != null) {
                exists.setName(user.getName());
                exists.setEmail(user.getEmail());
                exists.setPhone(user.getPhone());
                exists.setPassword(user.getPassword());
                userRepository.save(exists);
                randomUser = new UserDTO(
                        exists.getId(),
                        exists.getName(),
                        exists.getEmail(),
                        exists.getPhone()
                );
            }
            else {
                throw new Exception("User not found");
            }
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(randomUser);
    }

    // DELETE
    @DeleteMapping(value = "/{id]")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        UserDTO randomUser = null;
        try {
            var exists = userRepository.findById(id).orElse(null);
            if (exists != null) {
                userRepository.deleteById(id);
            }

        else {
            throw new Exception("User not found");
        }
    }
        catch (Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

        return ResponseEntity.noContent().build();
    }



}
