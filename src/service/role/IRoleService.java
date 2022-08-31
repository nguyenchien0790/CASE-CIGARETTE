package service.role;

import model.Role;
import model.RoleName;

import java.util.List;

public interface IRoleService {

    List<Role> findAll();

    void save(Role role);

    Role findByRoleName(RoleName roleName);

}
