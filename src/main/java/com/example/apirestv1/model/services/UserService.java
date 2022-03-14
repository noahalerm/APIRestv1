package com.example.apirestv1.model.services;

import com.example.apirestv1.model.entities.User;
import com.example.apirestv1.model.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    //ATTRIBUTES
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    //METHODS
    /**
     * This method is used to get a user by their username.
     * @param username Username
     * @return User
     */
    public User consultByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    /**
     * This method is used to create a new user.
     * @param newUser New User
     * @return User
     */
    public User createNewUser(User newUser) {
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        return newUser;
    }

    /**
     * This method is used to list all users.
     * @return User list
     */
    public List<User> listUsers(){
        return userRepository.findAll();
    }
}
