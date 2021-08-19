package com.covengers.springapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private Long no;

    @NotNull
    private String username;
    @NotNull
    private String name;
    @NotNull
    private String password;
    private String nickname;
    @NotNull
    private Boolean isDeleted;
    @NotNull
    private String backgroundImageUrl;
    @NotNull
    private String profileImageUrl;
    @NotNull
    private String role;
    private String token;
    @NotNull
    private Date createdAt;
    private Date lastLoginAt;
}
