package com.banking.springboot.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auths")
public class AuthGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private String username;

    @Column
    private String authUserGroup;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthGroup() {
        return authUserGroup;
    }

    public void setAuthGroup(String authGroup) {
        this.authUserGroup = authGroup;
    }

    @Override
    public String toString() {
        return "AuthGroup [authGroup=" + authUserGroup + ", id=" + id + ", username=" + username + "]";
    }

}
