package com.tomdoischer.phrasedemo.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.tomdoischer.phrasedemo.entity.PhraseAccountConfiguration;
import com.tomdoischer.phrasedemo.pojo.PhraseAccountConfigurationPojo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.Optional;

@Service
public class AuthenticationClient {

    public String getApiKey(PhraseAccountConfiguration accountConfiguration) {
        WebClient client = WebClient.builder()
                .baseUrl("https://cloud.memsource.com/web/api2/v1")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().wiretap(true)
                ))
                .build();
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.POST);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/auth/login");
        PhraseAccountConfigurationPojo config = new PhraseAccountConfigurationPojo(accountConfiguration);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(config);
        WebClient.ResponseSpec responseSpec = headersSpec.header(
                        HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve();
        String response = responseSpec.bodyToMono(String.class).block();
        return getApiKeyFromJson(response);
    }

    private String getApiKeyFromJson(String response) throws JsonSyntaxException {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        return jsonObject.get("token").getAsString();
    }
}
