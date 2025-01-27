package com.guessenglishactor.Interfaces;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface IhttpClientExecutor {
    HttpResponse<String> sendRequest(HttpRequest request);
}