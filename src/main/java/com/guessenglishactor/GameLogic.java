package com.guessenglishactor;

import java.util.HashSet;

import com.guessenglishactor.Interfaces.IAuthentication;
import com.guessenglishactor.Interfaces.IhttpClientExecutor;
import com.guessenglishactor.Interfaces.IhttpRequest;

public class GameLogic {
    private boolean Active;
    private HashSet<String> AllCharacters;
    private int properlyGuessedCharacters;
    private IhttpRequest requestCreator;
    private IhttpClientExecutor clientExecutor;
    private IAuthentication authenticationCode;

    public GameLogic(IhttpRequest requestCreator, IhttpClientExecutor clientExecutor, IAuthentication authenticationCode) {
        Active = true;
        this.requestCreator = requestCreator;
        this.clientExecutor = clientExecutor;
        this.authenticationCode = authenticationCode;

        RequestCharactersRawData characterRawData = new RequestCharactersRawData(requestCreator, clientExecutor, authenticationCode);
        ProcessCharacterData processedCharacterData = new ProcessCharacterData(characterRawData.getResponse());
        this.AllCharacters = processedCharacterData.getCharacters();}

    public boolean Selection(String Character){
        /*prevents caps from killing the comparison */
        if(AllCharacters.contains(Character.toLowerCase())){
            AllCharacters.remove(Character);
            properlyGuessedCharacters++;
            return(true);
        }
        else{
            return(false);
        }
    }


    public boolean isActive() {
        return Active;
    }

    public void GameOver(){
        this.Active=false;
    }

    public int getProperlyGuessedCharacters() {
        return properlyGuessedCharacters;
    }
}
