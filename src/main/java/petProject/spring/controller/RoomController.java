package petProject.spring.controller;

import petProject.spring.dto.RoomDTO;
import petProject.spring.service.RoomService;
import petProject.spring.utils.ResponseEntityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

	private final RoomService service;

	@GetMapping
	public ResponseEntity<List<RoomDTO>> findAll() {
		return ResponseEntityUtils.okIfNotEmpty(
				service.getAll()
		);
	}

}
