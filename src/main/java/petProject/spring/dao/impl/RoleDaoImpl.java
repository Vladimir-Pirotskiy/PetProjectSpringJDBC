package petProject.spring.dao.impl;

import petProject.spring.dao.RoleDao;
import petProject.spring.persistance.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public RoleDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Role> findAll() {
		return jdbcTemplate.queryForList(
				"select * from role",
				new EmptySqlParameterSource(),
				Role.class
		);
	}

	@Override
	public Role findOne(String value) {
//		jdbcTemplate.query(
//				"select * from role where value = :value",
//				new MapSqlParameterSource()
//					.addValue("value", value),
//				(rs, rowIdx) -> new Role(rs.getString(0))
//		);

		return jdbcTemplate.queryForObject(
				"select * from role where value = :value",
				new MapSqlParameterSource()
						.addValue("value", value),
				Role.class
		);
	}

	@Override
	@Transactional
	public Role save(Role role) {
		var affectedRows = jdbcTemplate.update(
				"insert into role (value) values (:role)",
				new MapSqlParameterSource("role", role.getValue())
		);

		if (affectedRows > 0) {
			return jdbcTemplate.queryForObject(
					"select * from role where value = :role",
					new MapSqlParameterSource("role", role.getValue()),
					Role.class
			);
		}

		return new Role();
	}

	@Override
	public Role update(Role oldRole, Role newRole) {
		jdbcTemplate.update(
				"update role set value = :newRole where value = :oldRole",
				new MapSqlParameterSource()
						.addValue("oldRole", oldRole.getValue())
						.addValue("newRole", newRole.getValue())
		);

		return newRole;
	}
}
