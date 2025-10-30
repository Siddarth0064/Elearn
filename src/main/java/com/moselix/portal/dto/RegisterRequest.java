package com.moselix.portal.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisterRequest {


    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String gender;
    private LocalDate dob;
    private String address;
    private String password;  // Plaintext from frontend

}

