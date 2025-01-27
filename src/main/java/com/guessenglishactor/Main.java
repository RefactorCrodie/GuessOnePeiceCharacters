package com.guessenglishactor;

import java.util.Scanner;

import com.guessenglishactor.Interfaces.IAuthentication;
import com.guessenglishactor.Interfaces.IhttpClientExecutor;
import com.guessenglishactor.Interfaces.IhttpRequest;

public class Main {
    public static void main(String[] args) {
        IhttpRequest requestCreator = new DefaultHttpRequestCreator();
        IhttpClientExecutor clientExecutor = new DefaultHttpClientExecutor();
        IAuthentication authenticationCode = new DefaultAuthentication("Enter auth code for https://api.themoviedb.org/3");
        
        GameLogic characterDataService = new GameLogic(requestCreator, clientExecutor, authenticationCode);
        Scanner PlayerResponse = new Scanner(System.in);
        GameCLI gameCLI = new GameCLI(characterDataService,PlayerResponse);
        gameCLI.start();

    }
}