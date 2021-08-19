package com.covengers.springapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @NotNull
    private String title;
    private String password;
    @NotNull
    private String type;
    @NotNull
    private Date createdAt;
    @Column(name = "headcount")
    private Integer headCount;

}
