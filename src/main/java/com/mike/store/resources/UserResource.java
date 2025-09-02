package com.mike.store.resources;

import com.mike.store.entities.DTO.UserDTO;
import com.mike.store.entities.User;
import com.mike.store.repository.UserRepository;
import com.mike.store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    // injecao de dependencia do service
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    // GET
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User randomUser = null;
        try {
            var exists = userService.getById(id);
           /* if (exists != null) {
                randomUser = new UserDTO(
                        exists.getId(),
                        exists.getName(),
                        exists.getEmail(),
                        exists.getPhone()
                );
            }*/
            randomUser = exists;

        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(randomUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {

        var randomUsers = new ArrayList<User>();

        try {
            List<User> users = userService.getAll();
            /*users.forEach(user -> randomUsers.add(
                    new UserDTO(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            user.getPhone()
                    )
            ));*/
            randomUsers.addAll(users);
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(randomUsers);
    }


    // POST
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            /*var randomUser = new UserDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPhone()
            );
            userService.insert(user);*/
            var u = userService.insert(user);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(uri).body(u);

        }
        catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }

    }

    // PUT
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> putUser(@PathVariable Long id, @RequestBody User user) {
        User randomUser = null;

        try {
            var exists = userService.update(id, user);
           /* if (exists != null) {
                randomUser = new UserDTO(
                        exists.getId(),
                        exists.getName(),
                        exists.getEmail(),
                        exists.getPhone()
                );
            }*/
            randomUser = exists;
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(randomUser);
    }

    // DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User randomUser = null;
        try {
            var exists = userService.delete(id);
            return ResponseEntity.noContent().build();
        }
            catch (Exception ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }



}
