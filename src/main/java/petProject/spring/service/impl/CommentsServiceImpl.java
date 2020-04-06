package petProject.spring.service.impl;

import petProject.spring.dao.AuthorDao;
import petProject.spring.dao.CommentsDao;
import petProject.spring.dao.PostDao;
import petProject.spring.dto.CommentRequest;
import petProject.spring.persistance.Author;
import petProject.spring.persistance.Comment;
import petProject.spring.persistance.Post;
import petProject.spring.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl implements CommentsService {

	private AuthorDao authorDao;
	private PostDao postDao;
	private CommentsDao commentsDao;

	@Autowired
	public CommentsServiceImpl(AuthorDao authorDao, PostDao postDao, CommentsDao commentsDao) {
		this.authorDao = authorDao;
		this.postDao = postDao;
		this.commentsDao = commentsDao;
	}

	@Override
	public void addComment(CommentRequest request) {
		Author author = authorDao.findByName(request.getAuthorName()).get(0);
		Post post = postDao.findById(request.getPostId());

		commentsDao.addComment(
				new Comment(
						request.getComment(),
						null,
						author.getId(),
						post.getId()
				)
		);
	}
}
