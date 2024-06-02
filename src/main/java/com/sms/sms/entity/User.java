package com.sms.sms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Student")
public class User{
   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String faculty;
    private String mobileNumber;
    private String email;

}
