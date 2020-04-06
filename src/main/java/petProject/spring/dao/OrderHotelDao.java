package petProject.spring.dao;

import petProject.spring.dto.OrderHotelDTO;
import petProject.spring.persistance.OrderHotel;

public interface OrderHotelDao {

	OrderHotelDTO create(OrderHotel order);

}
