package com.guessenglishactor;

import java.util.Scanner;

import com.guessenglishactor.Interfaces.IAuthentication;
import com.guessenglishactor.Interfaces.IhttpClientExecutor;
import com.guessenglishactor.Interfaces.IhttpRequest;

public class Main {
    public static void main(String[] args) {
        IhttpRequest requestCreator = new DefaultHttpRequestCreator();
        IhttpClientExecutor clientExecutor = new DefaultHttpClientExecutor();
        IAuthentication authenticationCode = new DefaultAuthentication("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZjJhOWQzODcwODBjMzhkMjI0NmYwZDJiZGM5NTlhZCIsInN1YiI6IjY2NTRmYjYyOTIyN2YzM2E2ZmNkYjZiNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.seCIxdLuRv5fGregO6r-5nGYrP0INdkn3PrOwcTg1J0");
        
        GameLogic characterDataService = new GameLogic(requestCreator, clientExecutor, authenticationCode);
        Scanner PlayerResponse = new Scanner(System.in);
        GameCLI gameCLI = new GameCLI(characterDataService,PlayerResponse);
        gameCLI.start();

    }
}