package com.tomdoischer.phrasedemo.service;

import com.tomdoischer.phrasedemo.client.AuthenticationClient;
import com.tomdoischer.phrasedemo.entity.PhraseAccountConfiguration;
import com.tomdoischer.phrasedemo.repository.PhraseAccountConfigurationRepository;
import org.springframework.stereotype.Service;

@Service
public class PhraseAccountConfigurationService {

    private final PhraseAccountConfigurationRepository accountConfigurationRepository;
    private final AuthenticationClient authenticationClient;

    public PhraseAccountConfigurationService(PhraseAccountConfigurationRepository accountConfigurationRepository,
                                             AuthenticationClient authenticationClient) {
        this.accountConfigurationRepository = accountConfigurationRepository;
        this.authenticationClient = authenticationClient;
    }

    public PhraseAccountConfiguration saveAccountConfig(PhraseAccountConfiguration configuration) {
        String apiKey = authenticationClient.getApiKey(configuration);
        configuration.setApiKey(apiKey);
        return accountConfigurationRepository.save(configuration);
    }

    public PhraseAccountConfiguration getById(Long id) {
        return accountConfigurationRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public PhraseAccountConfiguration getByUsername(String username) {
        return accountConfigurationRepository.findByUsername(username);
    }

    public PhraseAccountConfiguration update(Long id, PhraseAccountConfiguration configuration) {
        String apiKey = authenticationClient.getApiKey(configuration);
        configuration.setApiKey(apiKey);
        return accountConfigurationRepository.save(configuration);
    }
}
