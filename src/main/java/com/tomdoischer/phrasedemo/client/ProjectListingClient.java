package com.tomdoischer.phrasedemo.client;

import com.google.gson.Gson;
import com.tomdoischer.phrasedemo.entity.PhraseAccountConfiguration;
import com.tomdoischer.phrasedemo.pojo.ProjectsWrapperPojo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class ProjectListingClient {
    public ProjectsWrapperPojo listAllProjectsForAccount(PhraseAccountConfiguration accountConfiguration) {
        WebClient client = WebClient.builder()
                .baseUrl("https://cloud.memsource.com/web/api2/v1")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/projects");
        WebClient.RequestBodySpec spec = bodySpec.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "ApiToken " + accountConfiguration.getApiKey());
        WebClient.ResponseSpec responseSpec = spec.retrieve();
        String response = responseSpec.bodyToMono(String.class).block();
        return new Gson().fromJson(response, ProjectsWrapperPojo.class);
    }
}
