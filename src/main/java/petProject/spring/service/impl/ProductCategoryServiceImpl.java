package petProject.spring.service.impl;

import petProject.spring.dao.ProductCategoryDao;
import petProject.spring.persistance.ProductCategory;
import petProject.spring.service.ProductCategoryService;
import petProject.spring.utils.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

	private final ProductCategoryDao productCategoryDao;

	@Override
	public List<ProductCategory> getAll() {
		return productCategoryDao.findAll();
	}

	@Override
	public ProductCategory getById(Long id) {
		List<ProductCategory> products = productCategoryDao.findById(id);

		if (products.isEmpty()) {
			throw new ApiException("No product found with id  - " + id);
		}

		return products.get(0);
	}

	@Override
	public void save(ProductCategory productCategory) {
		productCategoryDao.save(productCategory);
	}

	@Override
	public void update(ProductCategory productCategory) {
		productCategoryDao.update(productCategory);
	}
}
