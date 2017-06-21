package org.security.business;

import org.security.dao.TokenRepository;
import org.security.entities.Token;
import org.security.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by admin on 19/06/2017.
 */

@Service
public class TokenBusinessImpl implements TokenBusiness {

    @Autowired
    TokenRepository tokenRepository;

    @Override
    public Token addToken(Token r) {
        return tokenRepository.save(r);
    }

    @Override
    public List<Token> getTokens() {

        return tokenRepository.findAll();


    }

    @Override
    public Token updateToken(Token token, Long id) {


        token.setId(id);

        return tokenRepository.saveAndFlush(token);
    }

    @Override
    public void deleteToken(Long id) {

        tokenRepository.delete(id);

    }

    @Override
    public Token setTokenForUser(User user, Token token) {
        token.setUser(user);
        return tokenRepository.save(token);

    }

    @Override
    public User getUser(String tkn) {
        System.out.println("TOKEN== " + tkn);
        Specification<Token> specification = new Specification<Token>() {
            @Override
            public Predicate toPredicate(Root<Token> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                String mToken = tkn == null ? "" : tkn;

                Predicate tokenPredicate = cb.like(root.get("tokenString"), mToken.trim());
                return cb.and(tokenPredicate);

            }
        };
        Token token = findOne(specification);
        if (token == null) return null;

        return token.getUser();
    }

    public Token findOne(Specification<Token> specification) {
        return tokenRepository.findOne(specification);

    }


}
