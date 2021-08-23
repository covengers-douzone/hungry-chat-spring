package com.covengers.springapi.response;

public class UserInfo {

    private String username;
    private String name;
    private Object role;

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

    public Object getRoles() {
        return role;
    }

    public void setRoles(Object role) {
        this.role = role;
    }
}
