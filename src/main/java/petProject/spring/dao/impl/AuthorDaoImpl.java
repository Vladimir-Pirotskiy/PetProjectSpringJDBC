package petProject.spring.dao.impl;

import petProject.spring.dao.AuthorDao;
import petProject.spring.persistance.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Author> findByName(String name) {
		return jdbcTemplate.query(
				" select a.id, a.name, a.email from author a where a.name = :name ",
				new MapSqlParameterSource()
						.addValue("name", name),
				new BeanPropertyRowMapper<>(Author.class)
		);
	}
}
