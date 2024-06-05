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
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_email_unique", columnNames = "email"
                )
        }
)
public class User{
   @Id
   @SequenceGenerator(
           name = "user_sequence",
           sequenceName = "user_sequence",
           allocationSize = 1
   )
   @GeneratedValue(
           strategy = GenerationType.SEQUENCE,
           generator = "user_sequence"
   )
   @Column(
           name = "id",
           updatable = false
   )

   private Long id;
    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String username;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Transient
    private Integer age;


    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;
    private String faculty;
    private String mobileNumber;


}
