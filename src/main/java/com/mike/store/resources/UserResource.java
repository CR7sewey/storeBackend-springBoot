package com.mike.store.resources;

import com.mike.store.DTO.UserDTO;
import com.mike.store.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserResource {

    // injecao de dependencia do service

    // GET
    @GetMapping
    public ResponseEntity<UserDTO> getUser() {
        var randomUser = new UserDTO(
                1,
                "",
                "",
                ""
        );
        return ResponseEntity.ok().body(randomUser);
    }




    // POST

    // PUT

    // DELETE



}
