package org.security.business;

import org.security.dao.RouteRepository;
import org.security.entities.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by admin on 20/06/2017.
 */
@Service
public class RouteBusinessImpl implements RouteBusiness {

    @Autowired
    RouteRepository routeRepository;

    @Override
    public Route addRoute(Route r) {
        Route rr = from(r.getUri());

        if (rr == null)
            return routeRepository.save(r);
        rr.setPermission(r.getPermission());
        return routeRepository.saveAndFlush(rr);
    }

    @Override
    public List<Route> getRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route updateRoute(Route r, Long id) {
        r.setId(id);
        return routeRepository.save(r);
    }

    @Override
    public void deleteRoute(Long id) {

    }

    @Override
    public String getRole(String uri) {

        Specification<Route> specification = new Specification<Route>() {
            @Override
            public Predicate toPredicate(Root<Route> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

                return cb.equal(root.get("uri"), uri);

            }
        };
        Route one = routeRepository.findOne(specification);
        if (one != null) {

            return one.getPermission();
        }

        return "";
    }


    private Route from(String uri) {

        Specification<Route> specification = new Specification<Route>() {
            @Override
            public Predicate toPredicate(Root<Route> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

                return cb.equal(root.get("uri"), uri);

            }
        };
        return routeRepository.findOne(specification);

    }


}
