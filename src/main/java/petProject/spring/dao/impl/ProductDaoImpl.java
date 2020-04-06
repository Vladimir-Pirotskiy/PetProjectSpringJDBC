package petProject.spring.dao.impl;

import petProject.spring.dao.ProductDao;
import petProject.spring.dto.ProductDTO;
import petProject.spring.persistance.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<ProductDTO> findAll() {
		return jdbcTemplate.query(
				" select p.name as name," +
						" p.supplier as supplier, " +
						"  p.count as count ," +
						" pc.name as category " +
						" from product p" +
						" join product_category pc on p.category_id = pc.id ",
				new EmptySqlParameterSource(),
				new BeanPropertyRowMapper<>(ProductDTO.class)
		);
	}

	@Override
	public Optional<Product> findById(Long id) {
		List<Product> products = jdbcTemplate.query(
				"select * from product where id = :id",
				new MapSqlParameterSource("id", id),
				new BeanPropertyRowMapper<>(Product.class)
		);

		return products.stream().findFirst();
	}

	@Override
	public Optional<Product> findByNameAndCategory(String name, String category) {
		List<Product> products = jdbcTemplate.query(
				"select p.id, p.name, p.count, p.supplier, p.category_id from product p " +
						" join product_category pc on pc.id = p.category_id " +
						" where p.name = :name and pc.name = :category ",
				new MapSqlParameterSource()
						.addValue("name", name)
						.addValue("category", category),
				new BeanPropertyRowMapper<>(Product.class)
		);

		return products.stream().findFirst();
	}

	@Override
	public boolean save(Product product) {
		int affectedRows = jdbcTemplate.update(
				" insert into product (name, count, supplier, category_id) " +
						" values (:name, :count, :supplier, :category) ",
				new MapSqlParameterSource()
						.addValue("name", product.getName())
						.addValue("count", product.getCount())
						.addValue("supplier", product.getSupplier())
						.addValue("category", product.getCategoryId())
		);

		return affectedRows > 0;
	}

	@Override
	public void updateCount(Long productId, int count) {
		jdbcTemplate.update(
				"update product set count = :count where id = :id",
				new MapSqlParameterSource()
						.addValue("count", count)
						.addValue("id", productId)
		);
	}
}
