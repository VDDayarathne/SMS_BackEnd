package com.sms.sms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "sport_schedules")
public class SportSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String venue;
    private String time;
    private String sport;
    private String description; // indoor, outdoor1, outdoor2, swimming pool

}
