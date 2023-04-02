package com.tomdoischer.phrasedemo.service;

import com.tomdoischer.phrasedemo.client.AuthenticationClient;
import com.tomdoischer.phrasedemo.entity.PhraseAccountConfiguration;
import com.tomdoischer.phrasedemo.repository.PhraseAccountConfigurationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public PhraseAccountConfiguration getById(Long id) {
        return accountConfigurationRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public PhraseAccountConfiguration getByUsername(String username) {
        return accountConfigurationRepository.findByUsername(username);
    }

    public PhraseAccountConfiguration update(Long id, PhraseAccountConfiguration configuration) {
        String apiKey = authenticationClient.getApiKey(configuration);
        this.update(configuration, apiKey);

        return configuration;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(PhraseAccountConfiguration configuration, String apiKey) {
        accountConfigurationRepository.update(
                configuration.getUsername(),
                configuration.getPassword(),
                apiKey,
                configuration.getId());
    }
}
