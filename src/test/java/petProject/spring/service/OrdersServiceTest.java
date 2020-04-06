package petProject.spring.service;

import petProject.spring.dao.OrderDao;
import petProject.spring.dao.ProductDao;
import petProject.spring.dto.OrderRequest;
import petProject.spring.persistance.Orders;
import petProject.spring.persistance.Product;
import petProject.spring.service.impl.OrdersServiceImpl;
import petProject.spring.utils.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(SpringExtension.class)
class OrdersServiceTest {

	// в when могут быть только mock.
	// mock могут быть только интерфейсы (interface)

	// Для тестирования тестов! Если тест зеленый, изменить expected (1й параметр) результат

	@Mock
	private ProductDao productDao;
	@Mock
	private OrderDao orderDao;

	private OrdersService service;

	@BeforeEach
	void setUp() {
		initMocks(this);
//		productDao = Mockito.mock(ProductDao.class);

		service = new OrdersServiceImpl(productDao, orderDao);
	}

	@Test
	void processOrder() {
		Product product = new Product(1L, "name", 1123123, "sup", 11L);
		Mockito.doReturn(Optional.of(product))
				.when(productDao).findByNameAndCategory(any(String.class), anyString()); // todo: !!!!! СКОБКИ

		OrderRequest request = new OrderRequest("n", "n", 1);
		Orders order = service.createOrder(request);

		assertEquals(product.getId(), order.getProductId());
		assertEquals(request.getCount(), order.getCount());
	}

	@Test
	void testProcessOrderWhenRequestCountLessThenZero() {
		ApiException exception = assertThrows(
				ApiException.class,
				() -> service.createOrder(new OrderRequest("m", "c", -1))
		);

		assertEquals("Количество должно быть больше 0", exception.getReason());
	}

	@Test
	void testProcessOrderWhenProductCountLessThenZero() {
		Product product = new Product(1L, "n", -1, "s", 12L);

		doReturn(Optional.of(product))
				.when(productDao).findByNameAndCategory(anyString(), anyString());

		ApiException exception = assertThrows(
				ApiException.class,
				() -> service.createOrder(new OrderRequest("n", "c", 11))
		);

		assertEquals("Данный продукт недоступен", exception.getReason());
	}

	@Test
	void testProcessOrderWhenProductCountLessWhenRequest() {
		Product product = new Product(1L, "n", 5, "s", 4L);
		doReturn(Optional.of(product))
				.when(productDao).findByNameAndCategory(anyString(), anyString());

		ApiException exception = assertThrows(
				ApiException.class,
				() -> service.createOrder(new OrderRequest("", "", 7))
		);

		assertEquals("Нет такого количества товаров", exception.getReason());
	}
}
