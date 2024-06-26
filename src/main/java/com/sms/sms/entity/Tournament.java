package com.sms.sms.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Data
@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column(length = 2000)
    private String description;
    @CreationTimestamp
    @Column(updatable = false)
    private Date date;
    @Column(length = 1000)
    private String image;
    // getters and setters
}