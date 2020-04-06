package petProject.spring.dao.impl;

import petProject.spring.dao.PostDao;
import petProject.spring.dto.PostResponse;
import petProject.spring.dto.PostWithAuthor;
import petProject.spring.persistance.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Repository
@RequiredArgsConstructor
public class PostDaoImpl implements PostDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public int create(Post post) {
		var id = ThreadLocalRandom.current().nextInt(1, 1000);
		jdbcTemplate.update(
				"insert into post(id, text_news, date, author_id, title)" +
						" values (:id, :textNews, :date, :author, :title)",
				new MapSqlParameterSource()
							.addValue("id", id)
							.addValue("textNews", post.getTextNews())
							.addValue("date", Timestamp.valueOf(LocalDateTime.now()))
							.addValue("author", post.getAuthor().getId())
							.addValue("title", post.getTitle())
		);

		return id;
	}

	@Override
	public PostWithAuthor findById(int id) {
		return jdbcTemplate.queryForObject(
				"select p.id as id, " +
						"p.text_news as text, " +
						" p.date as dateTime, " +
						" p.title as title, " +
						" a.name as authorName, " +
						" a.email as authorEmail " +
						" from post p" +
						" join author a on p.author_id = a.id " +
						" where p.id = :id",
				new MapSqlParameterSource("id", id),
				new BeanPropertyRowMapper<>(PostWithAuthor.class)
		);
	}

	@Override
	public List<PostResponse> findByAuthor(String name) {
		return jdbcTemplate.query(
				"select  p.id as id," +
						" p.text_news as text," +
						" p.date as dateTime, " +
						" p.title as title " +
						" from post p " +
						" join author a on p.author_id = a.id " +
						" where a.name = :name ",
				new MapSqlParameterSource()
					.addValue("name", name),
				(rs, idx) -> new PostResponse(
						rs.getString("text"),
						rs.getString("title"),
						rs.getTimestamp("dateTime").toLocalDateTime(),
						rs.getLong("id")
				)
//				new BeanPropertyRowMapper<>(PostResponse.class)
		);
	}

	@Override
	public Post findById(long id) {
		return jdbcTemplate.query(
				"select * from post where id = :id",
				new MapSqlParameterSource("id", id),
				new BeanPropertyRowMapper<>(Post.class)
		).get(0);
	}

	@Override
	public void update(PostResponse response) {
		jdbcTemplate.update(
				" update post set text_news = :text, title  = :title where id = :id ",
				new MapSqlParameterSource()
						.addValue("text", response.getText())
						.addValue("title", response.getTitle())
						.addValue("id", response.getId())
		);
	}
}
