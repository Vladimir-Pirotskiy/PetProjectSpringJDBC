package petProject.spring.dao;

import petProject.spring.dto.PostResponse;
import petProject.spring.dto.PostWithAuthor;
import petProject.spring.persistance.Post;

import java.util.List;

public interface PostDao {

	int create(Post post);

	PostWithAuthor findById(int id);

	Post findById(long id);

	List<PostResponse> findByAuthor(String name);

	void update(PostResponse response);
}
