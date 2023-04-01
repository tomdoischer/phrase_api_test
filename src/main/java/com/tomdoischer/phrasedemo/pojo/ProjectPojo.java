package com.tomdoischer.phrasedemo.pojo;

import java.util.List;

/**
 * Name, status, source language and target languages should be displayed
 *
 */
public class ProjectPojo {
    private String name;
    private String status;
    private String sourceLang;
    private List<String> targetLangs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSourceLang() {
        return sourceLang;
    }

    public void setSourceLang(String sourceLang) {
        this.sourceLang = sourceLang;
    }

    public List<String> getTargetLangs() {
        return targetLangs;
    }

    public void setTargetLangs(List<String> targetLangs) {
        this.targetLangs = targetLangs;
    }
}
