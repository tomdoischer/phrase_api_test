package com.tomdoischer.phrasedemo.pojo;

import java.util.List;

/**
 * Object used to parse the JSON response in {@link com.tomdoischer.phrasedemo.client.ProjectListingClient}.
 */
public class PhraseProjectsWrapperPojo {
    private List<PhraseProjectPojo> content;

    public List<PhraseProjectPojo> getContent() {
        return content;
    }

    public void setContent(List<PhraseProjectPojo> content) {
        this.content = content;
    }
}
