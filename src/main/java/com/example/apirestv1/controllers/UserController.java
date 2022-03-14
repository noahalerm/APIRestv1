package com.example.apirestv1.controllers;

import com.example.apirestv1.model.entities.User;
import com.example.apirestv1.model.entities.UserDTO;
import com.example.apirestv1.model.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    //ATTRIBUTES
    private final UserService userService;

    //METHODS

    //GET
    @GetMapping("/me")
    public UserDTO consultMe(@AuthenticationPrincipal User user) {
        return new UserDTO(user.getUsername(), user.getAvatar(), user.getRole());
    }

    //GET
    @GetMapping("/users")
    public ResponseEntity<?> listUsersDTO() {
        //LISTS
        List<User> res = userService.listUsers();
        List<UserDTO> aux = new ArrayList();

        //INSERT LOOP
        for (User user : res) {
            aux.add(new UserDTO(user.getUsername(), user.getAvatar(), user.getRole()));
        }

        //OUTPUT
        if (res.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(aux);
    }

    //POST
    @PostMapping("/users")
    public ResponseEntity<?> createNewUser (@RequestBody User newUser) {
        try {
            User res = userService.createNewUser(newUser);
            UserDTO user = new UserDTO(res.getUsername(), res.getAvatar(), res.getRole());

            return new ResponseEntity(user, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
