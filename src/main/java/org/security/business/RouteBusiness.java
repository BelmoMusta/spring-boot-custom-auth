package org.security.business;


import org.security.entities.Route;

import java.util.List;

public interface RouteBusiness {

    public Route addRoute(Route r);

    public List<Route> getRoutes();

    public Route updateRoute(Route r, Long id);

    public void deleteRoute(Long id);

    public String getRole(String uri);
}
