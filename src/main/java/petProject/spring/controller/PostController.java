package petProject.spring.controller;

import petProject.spring.dto.PostCreationRequest;
import petProject.spring.dto.PostResponse;
import petProject.spring.dto.PostWithAuthor;
import petProject.spring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@GetMapping
	public ResponseEntity<List<PostResponse>> getByAuthorName(@RequestParam String authorName) {
		try {
			List<PostResponse> allByAuthor = postService.getAllByAuthor(authorName);

			return ResponseEntity.ok(allByAuthor);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(
					List.of(PostResponse.builder().error(e.getMessage()).build())
			);
		}
	}

	@PostMapping
	public ResponseEntity<PostWithAuthor> create(@RequestBody PostCreationRequest request) {
		return ResponseEntity.ok(
				postService.create(request)
		);
	}

	@PutMapping
	public ResponseEntity<PostResponse> update(@RequestBody PostResponse response) {
		return ResponseEntity.ok(
				postService.update(response)
		);
	}

}
