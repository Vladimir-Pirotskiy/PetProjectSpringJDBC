package petProject.spring.service;

import petProject.spring.dto.ProductDTO;
import petProject.spring.dto.ProductForSaleDTO;
import petProject.spring.persistance.Product;

import java.util.List;

public interface ProductService {

	List<ProductDTO> getAll();

	List<ProductForSaleDTO> getAllForSale();

	void addToWarehouse(Product product);

}
