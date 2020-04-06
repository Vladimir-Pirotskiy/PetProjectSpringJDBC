package petProject.spring.service.impl;

import petProject.spring.dao.AuthorDao;
import petProject.spring.dao.PostDao;
import petProject.spring.dto.PostCreationRequest;
import petProject.spring.dto.PostResponse;
import petProject.spring.dto.PostWithAuthor;
import petProject.spring.persistance.Author;
import petProject.spring.persistance.Post;
import petProject.spring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

	private final PostDao postDao;
	private final AuthorDao authorDao;

	@Override
	public PostWithAuthor create(PostCreationRequest post) {
		Author author = checkAndGetAuthor(post.getAuthorName());

		var postID = postDao.create(
				Post.builder()
						.author(author)
						.title(post.getTitle())
						.textNews(post.getTextNews())
						.build()
		);

		return postDao.findById(postID);
	}

	@Override
	public List<PostResponse> getAllByAuthor(String name) {
		Author author = checkAndGetAuthor(name);

		return postDao.findByAuthor(author.getName());
	}

	private Author checkAndGetAuthor(String authorName) {
		List<Author> author = authorDao.findByName(authorName);

		if (author.isEmpty()) {
			throw new RuntimeException(String.format(
					"Author with name %s not found",
					authorName
			));
		}

		return author.get(0);
	}

	@Override
	public PostResponse update(PostResponse response) {
		postDao.update(response);

		Post fromDb = postDao.findById(response.getId());

		return new PostResponse(
				fromDb.getId(),
				fromDb.getTextNews(),
				fromDb.getTitle(),
				fromDb.getDate(),
				null
		);
	}
}
