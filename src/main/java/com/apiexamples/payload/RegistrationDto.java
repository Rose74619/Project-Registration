package com.apiexamples.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {
    private Long id;
    @Size(min =2,max = 100, message ="Enter name above than 2 character")
    private String name;
    @Email(message = "Enter valid email")
    private String email;
    private String phone;
    private String message;

}