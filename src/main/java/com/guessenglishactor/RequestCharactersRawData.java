package com.guessenglishactor;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.guessenglishactor.Interfaces.IAuthentication;
import com.guessenglishactor.Interfaces.IRequestRawData;
import com.guessenglishactor.Interfaces.IhttpClientExecutor;
import com.guessenglishactor.Interfaces.IhttpRequest;

public class RequestCharactersRawData implements IRequestRawData{
    private final String query = "/tv/37854/aggregate_credits?language=en-US";
    private final IhttpRequest requestCreator;
    private final IhttpClientExecutor clientExecutor;
    private final IAuthentication authentication;
    private HttpResponse<String> response;

    public RequestCharactersRawData(IhttpRequest requestCreator, IhttpClientExecutor clientExecutor, IAuthentication authentication) {
        this.requestCreator = requestCreator;
        this.clientExecutor = clientExecutor;
        this.authentication = authentication;
        executeRequest();
    }

    private void executeRequest() {
        try {
            HttpRequest requestTo = requestCreator.createRequest(query, authentication);
            this.response = clientExecutor.sendRequest(requestTo);
        } catch (Exception ex) {
            System.out.println("Issue: " + ex.getMessage());
        }
    }

    public HttpResponse<String> getResponse() {
        return response;
    }

    public static void main(String[] args) {
        IhttpRequest requestCreator = new DefaultHttpRequestCreator();
        IhttpClientExecutor clientExecutor = new DefaultHttpClientExecutor();
        IAuthentication authenticationCode = new DefaultAuthentication("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZjJhOWQzODcwODBjMzhkMjI0NmYwZDJiZGM5NTlhZCIsInN1YiI6IjY2NTRmYjYyOTIyN2YzM2E2ZmNkYjZiNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.seCIxdLuRv5fGregO6r-5nGYrP0INdkn3PrOwcTg1J0");
        RequestCharactersRawData s = new RequestCharactersRawData(requestCreator, clientExecutor, authenticationCode);

        if (s.getResponse() != null) {
            System.out.println(s.getResponse().body());
        } else {
            System.out.println("No response received.");
        }
    }
}