package org.security.dao;

import org.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by admin on 09/06/2017.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

}