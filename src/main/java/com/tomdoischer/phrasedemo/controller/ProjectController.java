package com.tomdoischer.phrasedemo.controller;

import com.tomdoischer.phrasedemo.dto.PhraseAccountConfigurationDto;
import com.tomdoischer.phrasedemo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public void getProjects() {
        projectService.listAllProjectsForAccount(null);
    }
}
