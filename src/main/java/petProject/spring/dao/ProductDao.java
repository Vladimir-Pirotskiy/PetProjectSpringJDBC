package petProject.spring.dao;

import petProject.spring.dto.ProductDTO;
import petProject.spring.persistance.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

	List<ProductDTO> findAll();

	Optional<Product> findById(Long id);

	Optional<Product> findByNameAndCategory(String name, String category);

	boolean save(Product product);

	void updateCount(Long productId, int count);
}
