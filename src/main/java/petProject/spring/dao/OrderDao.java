package petProject.spring.dao;

import petProject.spring.persistance.Orders;

import java.util.List;

public interface OrderDao {

	void createOrder(Orders order);

	List<Orders> findCreated();

	void changeStatus(Orders.OrderStatus status, List<Long> ids, Long adminId);
}
