package petProject.spring.service.impl;

import petProject.spring.dao.ProductDao;
import petProject.spring.dto.ProductDTO;
import petProject.spring.dto.ProductForSaleDTO;
import petProject.spring.persistance.Product;
import petProject.spring.service.ProductService;
import petProject.spring.utils.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductDao productDao;

	@Override
	public List<ProductDTO> getAll() {
		return productDao.findAll();
	}

	@Override
	public List<ProductForSaleDTO> getAllForSale() {
		return productDao.findAll().stream()
				.map(pd -> new ProductForSaleDTO(pd.getCategory(), pd.getName(), pd.getCount() > 0))
				.collect(Collectors.toList());
	}

	@Override
	public void addToWarehouse(Product product) {
		boolean isSaved = productDao.save(product);

		if (!isSaved) throw new ApiException("Save error");
	}
}
