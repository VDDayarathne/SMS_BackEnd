package com.sms.sms.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Data
@Entity
@Table(name = "registers")
public class ItemIssuingRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(updatable = false)
    private Date issuingTime;
    private String returningTime;
    @Convert(converter = ListToStringConverter.class)
    private List<String> items;
    private String description;
    private String studentId;


}



