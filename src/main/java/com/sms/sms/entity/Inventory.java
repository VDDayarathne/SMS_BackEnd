package com.sms.sms.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inventories")
@Data
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "sport_category_id")
    private SportCategory sportCategory;
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;


}
