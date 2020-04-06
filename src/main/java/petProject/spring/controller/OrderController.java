package petProject.spring.controller;

import petProject.spring.dto.OrderRequest;
import petProject.spring.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrdersService ordersService;

	@PostMapping
	public ResponseEntity<Void> publish(@RequestBody OrderRequest request) {
		ordersService.createOrder(request);

		return ResponseEntity.ok().build();
	}

	@PutMapping
	public ResponseEntity<Void> process() {
		ordersService.processOrder();

		return ResponseEntity.ok().build();
	}

}
