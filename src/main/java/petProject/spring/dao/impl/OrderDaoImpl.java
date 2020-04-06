package petProject.spring.dao.impl;

import petProject.spring.dao.OrderDao;
import petProject.spring.persistance.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void createOrder(Orders order) {
		jdbcTemplate.update(
				"insert into orders (status, count, product_id, user_id) " +
						" values (:status, :count, :product, :user) ",
				new MapSqlParameterSource()
						.addValue("status", Orders.OrderStatus.CREATED.name())
						.addValue("count", order.getCount())
						.addValue("product", order.getProductId())
						.addValue("user", order.getUserId())
		);
	}

	@Override
	public List<Orders> findCreated() {
		return jdbcTemplate.query(
				"select * from orders where status = 'CREATED'",
				new EmptySqlParameterSource(),
				new BeanPropertyRowMapper<>(Orders.class)
		);
	}

	@Override
	public void changeStatus(Orders.OrderStatus status, List<Long> ids, Long adminId) {
		jdbcTemplate.update(
				"update orders set status = :status, admin_id = :admin where id in (:ids) ",
				new MapSqlParameterSource()
						.addValue("status", status.name())
						.addValue("ids", ids)
						.addValue("admin", adminId)
		);
	}
}
