package petProject.spring.dao;

import petProject.spring.persistance.Role;

import java.util.List;

public interface RoleDao {

	// CRUD - create read update delete

	List<Role> findAll();

	Role findOne(String value);

	Role save(Role role);

	Role update(Role oldRole, Role newRole);
}
