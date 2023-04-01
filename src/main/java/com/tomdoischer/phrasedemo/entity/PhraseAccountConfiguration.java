package com.tomdoischer.phrasedemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "phrase-account-configuration")
public class PhraseAccountConfiguration {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="username", nullable=false, unique=true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "api-key")
    private String apiKey;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
