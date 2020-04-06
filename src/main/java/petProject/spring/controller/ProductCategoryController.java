package petProject.spring.controller;

import petProject.spring.persistance.ProductCategory;
import petProject.spring.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/product-categories")
public class ProductCategoryController {

	private final ProductCategoryService productCategoryService;

	@GetMapping
	public ResponseEntity<List<ProductCategory>> findAll() {
		List<ProductCategory> products = productCategoryService.getAll();

		if (products.isEmpty()) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(products);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductCategory> findById(@PathVariable Long id) {
		ProductCategory product = productCategoryService.getById(id);
		if (product != null) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping
	public ResponseEntity<Void> save(@RequestBody ProductCategory productCategory) {
		productCategoryService.save(productCategory);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Void> update(@RequestBody ProductCategory productCategory) {
		productCategoryService.update(productCategory);

		return ResponseEntity.ok().build();
	}
}
