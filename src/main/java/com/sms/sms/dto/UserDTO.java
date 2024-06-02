package com.sms.sms.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class UserDTO {
    @Id
    private int id;
    private String username;
    private String password;
    private String faculty;
    private String mobileNumber;

}
