package petProject.spring.dao.impl;

import petProject.spring.dao.TransactionsDao;
import petProject.spring.dto.TransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionsDaoImpl implements TransactionsDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<TransactionDTO> findAll() {
		return jdbcTemplate.query(
				"select  " +
						" t.id as id, " +
						" t.amount as amount, " +
						" t.tmpt as date, " +
						" t.f_type as familyMember," +
						" i.type as income, " +
						" o.type as outcome " +
						" from transactions t " +
						" join family f using(f_type) " +
						" left join income i on t.income_id = i.id " +
						" left join outcome o on t.outcome_id = o.id " +
						" limit 50 ",
				new BeanPropertyRowMapper<>(TransactionDTO.class)
		);
	}
}
