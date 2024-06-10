package com.sms.sms.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@Data@ToString
@Table(name = "events",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "events_title", columnNames = "title"
                )
        }
)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private Date date;

}
