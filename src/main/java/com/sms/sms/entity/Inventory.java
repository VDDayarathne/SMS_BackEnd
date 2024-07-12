package com.sms.sms.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Data
@Table(name = "inventories")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "sport_category_id")
    private SportCategory sportCategory;
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date updatedAt;
    // getters and setters
}