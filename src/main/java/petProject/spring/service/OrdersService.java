package petProject.spring.service;

import petProject.spring.dto.OrderRequest;
import petProject.spring.persistance.Orders;

public interface OrdersService {

	Orders createOrder(OrderRequest request);

	void processOrder();
}
