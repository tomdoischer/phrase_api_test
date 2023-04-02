package com.tomdoischer.phrasedemo.service;

import com.tomdoischer.phrasedemo.client.ProjectListingClient;
import com.tomdoischer.phrasedemo.entity.PhraseAccountConfiguration;
import com.tomdoischer.phrasedemo.pojo.PhraseProjectPojo;
import com.tomdoischer.phrasedemo.pojo.PhraseProjectsWrapperPojo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhraseProjectService {

    private final PhraseAccountConfigurationService accountConfigurationService;
    private final ProjectListingClient projectListingClient;

    public PhraseProjectService(PhraseAccountConfigurationService accountConfigurationService, ProjectListingClient projectListingClient) {
        this.accountConfigurationService = accountConfigurationService;
        this.projectListingClient = projectListingClient;
    }

    /**
     * Get all projects owned by an account.
     *
     * @param id
     * @return
     */
    public List<PhraseProjectPojo> listAllProjectsForAccount(Long id) {
        PhraseAccountConfiguration accountConfiguration = accountConfigurationService.getById(id);
        PhraseProjectsWrapperPojo phraseProjectsWrapperPojo = projectListingClient.listAllProjectsForAccount(accountConfiguration);
        return phraseProjectsWrapperPojo.getContent();
    }
}
