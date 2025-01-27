package com.guessenglishactor;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guessenglishactor.Interfaces.IAuthentication;
import com.guessenglishactor.Interfaces.IRequestRawData;
import com.guessenglishactor.Interfaces.IhttpClientExecutor;
import com.guessenglishactor.Interfaces.IhttpRequest;

public class ProcessCharacterData {

    private HashSet<String> characters;
    private String httpResponseString;

    public ProcessCharacterData(HttpResponse<String> httpResponse) {
        this.httpResponseString = httpResponse.body();
        this.characters = new HashSet<>();
        processCharacterData();
    }

    private void processCharacterData() {
        JsonNode jsonCastMembers = initializeJsonNode();
        if (jsonCastMembers != null) {
            for (JsonNode castMember : jsonCastMembers) {
                JsonNode jsonCastRoles = castMember.path("roles");
                for (JsonNode castRole : jsonCastRoles) {
                    String character = castRole.path("character").asText();
                    if (isValidCharacter(character)) {
                        characters.add(removeVoiceTag(character.toLowerCase()));
                    }
                }
            }
        }
    }

    private JsonNode initializeJsonNode() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNodeRoot = objectMapper.readTree(this.httpResponseString);
            return jsonNodeRoot.path("cast");
        } catch (IOException e) {
            Logger.getLogger(ProcessCharacterData.class.getName()).log(Level.SEVERE, "Error initializing JSON node", e);
            return null;
        }
    }

    private boolean isValidCharacter(String character) {
        return character != null && !character.isEmpty();
    }

    private String removeVoiceTag(String character) {
        /*very limited experience with regex; though, it offers more efficent processing compared to 3 if loop checks. */
        return character.replaceAll("\\s*(?:\\(voice\\)|-\\s*voice|\\(Voice\\))\\s*", "").trim();
    }

    public HashSet<String> getCharacters() {
        return characters;
    }

    public static void main(String[] args) {
        IhttpRequest requestCreator = new DefaultHttpRequestCreator();
        IhttpClientExecutor clientExecutor = new DefaultHttpClientExecutor();
        IAuthentication authenticationCode = new DefaultAuthentication("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZjJhOWQzODcwODBjMzhkMjI0NmYwZDJiZGM5NTlhZCIsInN1YiI6IjY2NTRmYjYyOTIyN2YzM2E2ZmNkYjZiNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.seCIxdLuRv5fGregO6r-5nGYrP0INdkn3PrOwcTg1J0");
        IRequestRawData s = new RequestCharactersRawData(requestCreator, clientExecutor, authenticationCode);
        ProcessCharacterData n = new ProcessCharacterData(s.getResponse());

        System.out.println(n.getCharacters().toString());
    }
}
