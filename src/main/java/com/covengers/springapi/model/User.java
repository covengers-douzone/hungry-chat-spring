package com.covengers.springapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    private String phoneNumber;
    @NotNull
    @CreationTimestamp
    private Date createdAt;
    private Date lastLoginAt;
    @NotNull
    private String comments;

//    public List<String> getRoleList(){
//        if(this.role.length() > 0){
//            return Arrays.asList(this.role.split(","));
//        }
//        return new ArrayList<>();
//    }
}
