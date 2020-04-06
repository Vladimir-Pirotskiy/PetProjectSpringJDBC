package petProject.spring.service;

import petProject.spring.dto.OrderHotelDTO;
import petProject.spring.dto.OrderHotelRequest;

public interface OrderHotelService {

	OrderHotelDTO createRequest(OrderHotelRequest request);
}
