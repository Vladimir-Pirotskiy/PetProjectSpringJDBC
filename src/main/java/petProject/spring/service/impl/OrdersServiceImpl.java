package petProject.spring.service.impl;

import petProject.spring.dao.OrderDao;
import petProject.spring.dao.ProductDao;
import petProject.spring.dto.OrderRequest;
import petProject.spring.persistance.Orders;
import petProject.spring.persistance.Product;
import petProject.spring.service.OrdersService;
import petProject.spring.utils.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

	private final ProductDao productDao;
	private final OrderDao orderDao;


	@Override
	public Orders createOrder(OrderRequest request) {
		if (request.getCount() <= 0) {
			throw new ApiException("Количество должно быть больше 0");
		}

		Product product = productDao.findByNameAndCategory(request.getName(), request.getCategory())
				.orElseThrow(() -> new ApiException("Продукт не найден"));

		if (product.getCount() <= 0) {
			throw new ApiException("Данный продукт недоступен");
		}

		if (request.getCount() > product.getCount()) {
			throw new ApiException("Нет такого количества товаров");
		}

		Orders order = Orders.builder()
				.count(request.getCount())
				.productId(product.getId())
				.userId(1L)
				.build();

		orderDao.createOrder(order);

		return order;
	}

	@Override
	public void processOrder() {
		List<Orders> createdOrders = orderDao.findCreated();
		List<Long> ids = createdOrders.stream()
				.map(Orders::getId)
				.collect(Collectors.toList());


		createdOrders.forEach(order -> {
			Product product = productDao.findById(order.getId()).orElseThrow();
			int productCount = product.getCount();

			int finalCount = productCount - order.getCount();
			productDao.updateCount(product.getId(), finalCount);
		});

		orderDao.changeStatus(Orders.OrderStatus.COMPLETED, ids, 2L);
	}
}
