package org.security.service;


import org.security.business.RoleBusiness;
import org.security.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleRestService {
	@Autowired
    private RoleBusiness roleBusiness;

    @CrossOrigin
    @RequestMapping(value = "/api/role", method = RequestMethod.POST)
    public Role addRole(@RequestBody Role r){
        return roleBusiness.addRole(r);
    }
    @CrossOrigin
    @RequestMapping(value = "/api/roles", method = RequestMethod.GET)
    public List<Role> getRoles(){
        return roleBusiness.getRoles();
    }
    @CrossOrigin
    @RequestMapping(value = "/api/role/{id}", method = RequestMethod.PUT)
    public Role updateRole(@RequestBody Role r, @PathVariable Long id){
        return roleBusiness.updateRole(r,id);
    }
    @CrossOrigin
    @RequestMapping(value = "/api/role/{id}", method = RequestMethod.DELETE)
    public void deleteRole(@PathVariable Long id){
         roleBusiness.deleteRole(id);
    }
}

