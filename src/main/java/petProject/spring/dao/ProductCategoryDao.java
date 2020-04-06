package petProject.spring.dao;

import petProject.spring.persistance.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {

	List<ProductCategory> findAll();

	List<ProductCategory> findById(Long id);

	void save(ProductCategory productCategory);

	void update(ProductCategory productCategory);

}
