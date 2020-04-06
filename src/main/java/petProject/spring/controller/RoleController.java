package petProject.spring.controller;

import petProject.spring.dao.RoleDao;
import petProject.spring.persistance.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

	private final RoleDao roleDao;

	@GetMapping
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@GetMapping("/find-one")
	public Role findOne(@RequestParam String value) {
		return roleDao.findOne(value);
	}

	@PostMapping
	public Role save(@RequestBody Role role) {
		return roleDao.save(role);
	}

	@PutMapping
	public Role update (@RequestBody List<Role> role) {
		return roleDao.update(role.get(0), role.get(1));
	}

}
