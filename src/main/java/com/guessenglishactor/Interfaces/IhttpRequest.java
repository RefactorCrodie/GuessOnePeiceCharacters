package com.guessenglishactor.Interfaces;

import java.net.http.HttpRequest;

public interface IhttpRequest {
    HttpRequest createRequest(String parameters, IAuthentication authentication);
}