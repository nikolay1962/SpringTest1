package sda.sping.test1.repositories;

import org.springframework.data.repository.CrudRepository;
import sda.sping.test1.dto.AllowedRoles;

public interface AllowedRolesRepository extends CrudRepository<AllowedRoles, Integer> {

    AllowedRoles getAllowedRolesByRole(String role);
}
