package petProject.spring.dao.impl;

import petProject.spring.dao.VisitorDao;
import petProject.spring.persistance.Visitor;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class VisitorDaoImpl implements VisitorDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public Visitor create(Visitor visitor) {
		jdbcTemplate.update(
				"insert into visitor(id, first_name, last_name, email, password) " +
						"values (:id, :fName, :lName, :email, :pswd)",
				new MapSqlParameterSource()
						.addValue("id", visitor.getId())
						.addValue("fName", visitor.getFirstName())
						.addValue("lName", visitor.getLastName())
						.addValue("email", visitor.getEmail())
						.addValue("pswd", visitor.getPassword())
		);

		return visitor;
	}
}
