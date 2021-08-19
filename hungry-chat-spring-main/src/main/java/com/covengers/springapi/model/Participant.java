package com.covengers.springapi.model;

import javax.validation.constraints.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private Long no;
    @NotNull
    private String role;
    @NotNull
    private Boolean status;
    @NotNull
    private Date createdAt;
    @NotNull
    private Date lastReadAt;
    private String nickname;

    @ManyToOne
    @JoinColumn(name="userNo", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="roomNo", insertable = false, updatable = false)
    private Room room;
}

