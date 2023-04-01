package com.tomdoischer.phrasedemo.service;

import com.tomdoischer.phrasedemo.dto.PhraseAccountConfigurationDto;
import com.tomdoischer.phrasedemo.entity.PhraseAccountConfiguration;
import com.tomdoischer.phrasedemo.repository.PhraseAccountConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PhraseAccountConfigurationService {

    private final PhraseAccountConfigurationRepository accountConfigurationRepository;

    public PhraseAccountConfigurationService(PhraseAccountConfigurationRepository accountConfigurationRepository) {
        this.accountConfigurationRepository = accountConfigurationRepository;
    }

    public PhraseAccountConfiguration saveAccountConfig(PhraseAccountConfiguration configuration) {
        return accountConfigurationRepository.save(configuration);
    }

    public PhraseAccountConfiguration getById(Long id) {
        return accountConfigurationRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public PhraseAccountConfiguration getByUsername(String username) {
        return accountConfigurationRepository.findByUsername(username);
    }

    public PhraseAccountConfiguration update(Long id, PhraseAccountConfiguration configuration) {
//        PhraseAccountConfiguration oldConfiguration = accountConfigurationRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        configuration.setId(id);
        return accountConfigurationRepository.save(configuration);
    }
}
