package petProject.spring.dao.impl;

import petProject.spring.dao.RoomDao;
import petProject.spring.dto.RoomDTO;
import petProject.spring.persistance.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class RoomDaoImpl implements RoomDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<RoomDTO> findAll() {
		return jdbcTemplate.query(
				"select * from room r" +
						" join services s on r.services_id = s.id ",
				new EmptySqlParameterSource(),
				new BeanPropertyRowMapper<>(RoomDTO.class)
		);
	}

	@Override
	public Optional<Room> findByType(String type) {
		List<Room> rooms = jdbcTemplate.query(
				"select * from room where type = :type",
				new MapSqlParameterSource("type", type),
				new BeanPropertyRowMapper<>(Room.class)
		);

		return Optional.ofNullable(rooms).map(r -> r.get(0));
	}

	@Override
	public boolean isAvailable(UUID id, int count) {
		Integer countAvailable = jdbcTemplate.queryForObject(
				"select r.count from room r where id = :id ",
				new MapSqlParameterSource("id", id),
				Integer.class
		);

		return count <= countAvailable;
	}

	@Override
	public boolean reserveRoom(UUID id, int count) {
		int update = jdbcTemplate.update(
				"update room set count = :count where id = :id",
				new MapSqlParameterSource()
						.addValue("count", count)
						.addValue("id", id)
		);

		return update > 0;
	}
}
