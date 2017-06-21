package org.security.business;


import org.security.dao.RoleRepository;
import org.security.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleBusinessImpl implements RoleBusiness {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role addRole(Role r) {
        return roleRepository.save(r);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateRole(Role r, Long id) {
        r.setId(id);
        return roleRepository.saveAndFlush(r);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.delete(id);
    }
}
