package org.security.dao;

import org.security.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by admin on 09/06/2017.
 */
public interface RouteRepository extends JpaRepository<Route, Long>,JpaSpecificationExecutor<Route> {

}