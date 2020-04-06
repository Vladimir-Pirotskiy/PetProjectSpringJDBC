package petProject.spring.dao.impl;

import petProject.spring.dao.CommentsDao;
import petProject.spring.persistance.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class CommentsDaoImpl implements CommentsDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void addComment(Comment comment) {
		jdbcTemplate.update(
				" insert into comments (text_comment, date, author_id, post_id)" +
						" values (:text, :date, :author, :post) ",
				new MapSqlParameterSource()
						.addValue("text", comment.getTextComment())
						.addValue("date", Timestamp.valueOf(LocalDateTime.now()))
						.addValue("author", comment.getAuthorId())
						.addValue("post", comment.getPostId())
		);
	}
}
