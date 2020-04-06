package petProject.spring.controller;

import petProject.spring.dto.OrderDTO;
import petProject.spring.dto.OrderHotelDTO;
import petProject.spring.dto.OrderHotelRequest;
import petProject.spring.service.OrderHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/hotel-orders")
@RequiredArgsConstructor
public class OrderHotelController {

	private final OrderHotelService service;

	@GetMapping
	public ResponseEntity<OrderDTO> lala() {
		OrderDTO ret = OrderDTO.builder()
				.decimal(new BigDecimal("10.000"))
				.build();

		return ok(ret);
	}

	@PostMapping
	public ResponseEntity<OrderHotelDTO> create(@RequestBody OrderHotelRequest request) {
		return ok(
				service.createRequest(request)
		);
	}

}
