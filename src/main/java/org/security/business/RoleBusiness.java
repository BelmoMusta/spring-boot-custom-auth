package org.security.business;


import org.security.entities.Role;

import java.util.List;

public interface RoleBusiness {
    public Role addRole(Role r);

    public List<Role> getRoles();

    public Role updateRole(Role r, Long id);

    public void deleteRole(Long id);
}
