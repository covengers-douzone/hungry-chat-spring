package com.covengers.springapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Collection;
=======
import java.util.Arrays;
>>>>>>> 48ce93124e5e030068fb13209f675442a13d50e3
import java.util.Date;
import java.util.List;

@Data
@Entity
<<<<<<< HEAD
public class User implements UserDetails {
=======
@NoArgsConstructor
@AllArgsConstructor
public class User {
>>>>>>> 48ce93124e5e030068fb13209f675442a13d50e3
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
    //@NotNull
    //private String phoneNumber;
    @NotNull
    @CreationTimestamp
    private Date createdAt;
    private Date lastLoginAt;

<<<<<<< HEAD
    //private List<Authority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public void setBackgroundImageUrl(String backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /*public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }*/

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Date lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }
=======
//    public List<String> getRoleList(){
//        if(this.role.length() > 0){
//            return Arrays.asList(this.role.split(","));
//        }
//        return new ArrayList<>();
//    }
>>>>>>> 48ce93124e5e030068fb13209f675442a13d50e3
}
