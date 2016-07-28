package org.egdeveloper.service;

import org.egdeveloper.data.model.SecurityToken;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenGenerator implements IKeyGenerator<SecurityToken>{

    @Override
    public SecurityToken generate() {
        SecurityToken token = new SecurityToken();
        token.setGuid(UUID.randomUUID().toString());
        return token;
    }
}
