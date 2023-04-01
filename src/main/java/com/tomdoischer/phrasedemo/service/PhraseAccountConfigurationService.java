package com.tomdoischer.phrasedemo.service;

import com.tomdoischer.phrasedemo.dto.PhraseAccountConfigurationDto;
import com.tomdoischer.phrasedemo.entity.PhraseAccountConfiguration;
import com.tomdoischer.phrasedemo.repository.PhraseAccountConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Optional;

@Service
public class PhraseAccountConfigurationService {

    private final PhraseAccountConfigurationRepository accountConfigurationRepository;

    public PhraseAccountConfigurationService(PhraseAccountConfigurationRepository accountConfigurationRepository) {
        this.accountConfigurationRepository = accountConfigurationRepository;
    }

    public PhraseAccountConfiguration saveAccountConfig(PhraseAccountConfiguration configuration) {
        configuration = addApiKey(configuration);
        return accountConfigurationRepository.save(configuration);
    }

    public PhraseAccountConfiguration getById(Long id) {
        return accountConfigurationRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public PhraseAccountConfiguration getByUsername(String username) {
        return accountConfigurationRepository.findByUsername(username);
    }

    public PhraseAccountConfiguration update(Long id, PhraseAccountConfiguration configuration) {
        configuration.setId(id);
        configuration = addApiKey(configuration);
        return accountConfigurationRepository.save(configuration);
    }

    private PhraseAccountConfiguration addApiKey(PhraseAccountConfiguration accountConfiguration) {
        WebClient client = WebClient.builder()
                .baseUrl("https://cloud.memsource.com/web/api2/v1")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.Post);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/auth/login");
        WebClient.RequestBodySpec spec = bodySpec.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "ApiToken ");
        Mono<String> response = spec.retrieve()
                .bodyToMono(String.class);
        response.subscribe(
                value -> updateValue(value)
        );
    }
}
