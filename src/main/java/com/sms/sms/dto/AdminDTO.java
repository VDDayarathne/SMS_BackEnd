package com.sms.sms.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class AdminDTO {

        @Id
        private int id;
        private String adminName;
        private String adminEmail;
        private String password;
        private String faculty;
        private String title;
        private String description;
        private String notification;

}
