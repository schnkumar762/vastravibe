
package com.sachin.vastravibe.dto;

import lombok.Data;

@Data
public class SignupRequest {

    private String firstName;
    private String email;
    private String phone;
    private String password;
}
