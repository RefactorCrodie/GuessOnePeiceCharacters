package com.guessenglishactor;

import java.net.URI;
import java.net.http.HttpRequest;

import com.guessenglishactor.Interfaces.IAuthentication;
import com.guessenglishactor.Interfaces.IhttpRequest;

public class DefaultHttpRequestCreator implements IhttpRequest {

    @Override
    public HttpRequest createRequest(String parameters, IAuthentication authentication) {
        return HttpRequest.newBuilder()
            .uri(URI.create("https://api.themoviedb.org/3" + parameters))
            .header("accept", "application/json")
            .header("Authorization", "Bearer "+ authentication.getAuthenticationCode())
            .GET()
            .build();
    }
}