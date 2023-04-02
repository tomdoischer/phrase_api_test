package com.tomdoischer.phrasedemo.controller;

import com.tomdoischer.phrasedemo.service.PhraseProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The controller which lists Phrase projects owned by an account.
 */
@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "*")
public class PhraseProjectController {
    private final PhraseProjectService phraseProjectService;

    public PhraseProjectController(PhraseProjectService phraseProjectService) {
        this.phraseProjectService = phraseProjectService;
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<?> getProjects(@PathVariable Long userId) {
        return new ResponseEntity<>(phraseProjectService.listAllProjectsForAccount(userId), HttpStatus.OK);
    }
}
