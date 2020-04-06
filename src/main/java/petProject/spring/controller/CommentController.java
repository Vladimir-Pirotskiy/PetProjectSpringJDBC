package petProject.spring.controller;

import petProject.spring.dto.CommentRequest;
import petProject.spring.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	private CommentsService commentsService;

	@Autowired
	public CommentController(CommentsService commentsService) {
		this.commentsService = commentsService;
	}

	@PostMapping
	public ResponseEntity<Void> add(@RequestBody CommentRequest request) {
		commentsService.addComment(request);

		return ResponseEntity.ok().build();
	}

}

