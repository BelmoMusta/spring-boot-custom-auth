package org.security.dao;

import org.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by admin on 09/06/2017.
 */
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {
    @Query("SELECT u FROM User u  where u.username = :username and u.activated=true")
    public User getUserByUsername(@Param("username") String username);

}
