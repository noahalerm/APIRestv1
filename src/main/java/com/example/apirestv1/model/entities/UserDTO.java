package com.example.apirestv1.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    //ATTRIBUTES
    private String username;
    private String avatar;
    private String role;
}
