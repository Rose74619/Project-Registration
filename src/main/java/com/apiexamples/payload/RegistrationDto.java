package com.apiexamples.payload;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String message;

}