package petProject.spring.service.impl;

import petProject.spring.dao.AuthorDao;
import petProject.spring.dao.CommentsDao;
import petProject.spring.dao.PostDao;
import petProject.spring.dto.CommentRequest;
import petProject.spring.persistance.Author;
import petProject.spring.persistance.Comment;
import petProject.spring.persistance.Post;
import petProject.spring.service.CommentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(SpringExtension.class)
class CommentsServiceImplTest {

	@Mock
	private AuthorDao authorDao;
	@Mock
	private PostDao postDao;
	@Mock
	private CommentsDao commentsDao;

	private CommentsService service;

	@BeforeEach
	void setUp() {
		initMocks(this);

		this.service = new CommentsServiceImpl(authorDao, postDao, commentsDao);
	}

	@Test
	void addComment() {
		doReturn(List.of(new Author(1L ,"name", "email")))
				.when(authorDao).findByName(anyString());

		LocalDateTime now = LocalDateTime.now();

		Mockito.doReturn(new Post(1L, "titile", "text", now, null))
				.when(postDao).findById(anyLong());

		service.addComment(new CommentRequest("name", 22L, "comment"));

		verify(commentsDao).addComment(new Comment("comment", null, 1L, 1L));
	}
}
