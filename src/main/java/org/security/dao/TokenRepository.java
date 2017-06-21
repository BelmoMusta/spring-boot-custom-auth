package org.security.dao;

import org.security.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by admin on 09/06/2017.
 */
public interface TokenRepository extends JpaRepository<Token, Long>, JpaSpecificationExecutor<Token> {

}