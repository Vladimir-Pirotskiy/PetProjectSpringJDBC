package petProject.spring.service;

import petProject.spring.persistance.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

	List<ProductCategory> getAll();

	ProductCategory getById(Long id);

	void save(ProductCategory productCategory);

	void update(ProductCategory productCategory);
}
