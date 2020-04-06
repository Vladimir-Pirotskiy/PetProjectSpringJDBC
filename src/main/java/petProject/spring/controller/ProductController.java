package petProject.spring.controller;

import petProject.spring.dto.ProductDTO;
import petProject.spring.dto.ProductForSaleDTO;
import petProject.spring.persistance.Product;
import petProject.spring.service.ProductService;
import petProject.spring.utils.ResponseEntityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<ProductDTO> products = productService.getAll();

		return ResponseEntityUtils.okIfNotEmpty(
				products
		);
	}

	@GetMapping("/for-sale")
	public ResponseEntity<List<ProductForSaleDTO>> findAllForSale() {
		return ResponseEntityUtils.okIfNotEmpty(
				productService.getAllForSale()
		);
	}

	@PostMapping
	public ResponseEntity<Void> add(@RequestBody Product product) {
		productService.addToWarehouse(product);

		return ResponseEntity.ok().build();
	}
}
