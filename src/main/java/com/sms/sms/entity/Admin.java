package com.sms.sms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Data
@Table(name = "admin",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "admin_email_unique", columnNames = "adminEmail"
                )
        }
)

public class Admin {
    @Id
    @Column(
            name = "id",
            updatable = false
    )

    private Long id;
    @Column(
            name = "adminname",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String adminName;

    @Column(
            name = "adminEmail",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String adminEmail;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;
    private String title;
    private String description;
    @Column
    private String notification;


}


