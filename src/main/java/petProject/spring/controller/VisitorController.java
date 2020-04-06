package petProject.spring.controller;

import petProject.spring.persistance.Visitor;
import petProject.spring.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/visitors")
@RequiredArgsConstructor
public class VisitorController {

	private final VisitorService service;

	@PostMapping
	public ResponseEntity<Visitor> create(@RequestBody Visitor visitor) {

		return ResponseEntity.ok(
				service.register(visitor)
		);
	}

}
