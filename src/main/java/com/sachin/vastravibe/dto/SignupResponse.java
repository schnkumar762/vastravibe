
package com.sachin.vastravibe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupResponse {

    private Long id;
    private String firstName;
    private String email;
    private String phone;
    private String message;
}
