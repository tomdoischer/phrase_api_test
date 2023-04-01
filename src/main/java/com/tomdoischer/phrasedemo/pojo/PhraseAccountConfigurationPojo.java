package com.tomdoischer.phrasedemo.pojo;

import com.tomdoischer.phrasedemo.entity.PhraseAccountConfiguration;

public class PhraseAccountConfigurationPojo {
    private final String userName;
    private final String password;
    private final String code = "";

    public PhraseAccountConfigurationPojo (PhraseAccountConfiguration configuration) {
        this.userName = configuration.getUsername();
        this.password = configuration.getPassword();
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getCode() {
        return code;
    }
}
