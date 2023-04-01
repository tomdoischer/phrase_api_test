package com.tomdoischer.phrasedemo.service;

import com.tomdoischer.phrasedemo.client.ProjectListingClient;
import com.tomdoischer.phrasedemo.entity.PhraseAccountConfiguration;
import com.tomdoischer.phrasedemo.pojo.ProjectPojo;
import com.tomdoischer.phrasedemo.pojo.ProjectsWrapperPojo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final PhraseAccountConfigurationService accountConfigurationService;
    private final ProjectListingClient projectListingClient;

    public ProjectService(PhraseAccountConfigurationService accountConfigurationService, ProjectListingClient projectListingClient) {
        this.accountConfigurationService = accountConfigurationService;
        this.projectListingClient = projectListingClient;
    }

    public List<ProjectPojo> listAllProjectsForAccount(Long id) {
        PhraseAccountConfiguration accountConfiguration = accountConfigurationService.getById(id);
        ProjectsWrapperPojo projectsWrapperPojo = projectListingClient.listAllProjectsForAccount(accountConfiguration);
        return projectsWrapperPojo.getContent();
    }
}
