package com.tomdoischer.phrasedemo.controller;

import com.tomdoischer.phrasedemo.dto.PhraseAccountConfigurationDto;
import com.tomdoischer.phrasedemo.pojo.ProjectPojo;
import com.tomdoischer.phrasedemo.pojo.ProjectsWrapperPojo;
import com.tomdoischer.phrasedemo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<?> getProjects(@PathVariable Long userId) {
        return new ResponseEntity<>(projectService.listAllProjectsForAccount(userId), HttpStatus.OK);
    }
}
