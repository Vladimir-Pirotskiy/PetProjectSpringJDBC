package petProject.spring.dao.impl;

import petProject.spring.dao.OrderHotelDao;
import petProject.spring.dto.OrderHotelDTO;
import petProject.spring.persistance.OrderHotel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderHotelDaoImpl implements OrderHotelDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public OrderHotelDTO create(OrderHotel order) {
		jdbcTemplate.update(
				"insert into order_hotel (id, dates_count, visitor_id, room_id, services_id)" +
						" values (:id, :d, :v, :r, :s) ",
				new MapSqlParameterSource()
						.addValue("id", order.getId())
						.addValue("d", order.getDatesCount())
						.addValue("v", order.getVisitorId())
						.addValue("r", order.getRoomId())
						.addValue("s", order.getServicesId())
		);

		return jdbcTemplate.query(
				"select oh.id as id," +
						" v.email as visitorEmail," +
						" oh.dates_count as datesCount," +
						" r.type as roomType  " +
						" from order_hotel oh " +
						" join visitor v on oh.visitor_id = v.id" +
						"  join room r on oh.room_id = r.id" +
						" where oh.id = :id ",
				new MapSqlParameterSource("id", order.getId()),
				new BeanPropertyRowMapper<>(OrderHotelDTO.class)
		).get(0);
	}
}
