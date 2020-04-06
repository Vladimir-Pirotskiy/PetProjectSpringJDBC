package petProject.spring.service;

import petProject.spring.dto.PostCreationRequest;
import petProject.spring.dto.PostResponse;
import petProject.spring.dto.PostWithAuthor;

import java.util.List;

public interface PostService {

	PostWithAuthor create(PostCreationRequest post);

	List<PostResponse> getAllByAuthor(String name);

	PostResponse update(PostResponse response);


}
