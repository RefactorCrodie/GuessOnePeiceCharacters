package com.guessenglishactor;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.guessenglishactor.Interfaces.IhttpClientExecutor;

public class DefaultHttpClientExecutor implements IhttpClientExecutor {
    @Override
    public HttpResponse<String> sendRequest(HttpRequest request) {
        try {
            return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception ex) {
            System.out.println("Issue: " + ex.getMessage());
            return null;
        }
    }
}