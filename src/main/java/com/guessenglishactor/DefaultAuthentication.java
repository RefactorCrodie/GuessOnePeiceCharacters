package com.guessenglishactor;

import com.guessenglishactor.Interfaces.IAuthentication;

public class DefaultAuthentication implements IAuthentication {

    private final String authenticationCode;
    
    public DefaultAuthentication(String authenticationCode){
        this.authenticationCode = authenticationCode;
    }

    @Override
    public String getAuthenticationCode() {
        return authenticationCode;
    }
}
