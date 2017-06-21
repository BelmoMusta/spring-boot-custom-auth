package org.security.business;


import org.security.entities.Token;
import org.security.entities.User;

import java.util.List;

public interface TokenBusiness {
    public Token addToken(Token r);

    public List<Token> getTokens();

    public Token updateToken(Token r, Long id);

    public void deleteToken(Long id);
    public Token setTokenForUser(User user, Token token);

    User getUser(String token);
}
