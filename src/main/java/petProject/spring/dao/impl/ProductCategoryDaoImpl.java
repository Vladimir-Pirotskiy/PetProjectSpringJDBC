package petProject.spring.dao.impl;

import petProject.spring.dao.ProductCategoryDao;
import petProject.spring.persistance.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCategoryDaoImpl implements ProductCategoryDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<ProductCategory> findAll() {
		return jdbcTemplate.query(
				"select * from product_category",
				new EmptySqlParameterSource(),
				new BeanPropertyRowMapper<>(ProductCategory.class)
		);
	}

	@Override
	public List<ProductCategory> findById(Long id) {
		return jdbcTemplate.query(
				" select * from product_category where id = :id ",
				new MapSqlParameterSource("id", id),
				new BeanPropertyRowMapper<>(ProductCategory.class)
		);
	}

	@Override
	public void save(ProductCategory productCategory) {
		jdbcTemplate.update(
				"insert into product_category(name) values (:name)",
				new MapSqlParameterSource("name", productCategory.getName())
		);
	}

	@Override
	public void update(ProductCategory productCategory) {
		jdbcTemplate.update(
				"update product_category set name = :name where id = :id",
				new MapSqlParameterSource()
						.addValue("name", productCategory.getName())
						.addValue("id", productCategory.getId())
		);
	}
}
