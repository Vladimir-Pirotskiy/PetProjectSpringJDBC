package petProject.spring.service.impl;

import petProject.spring.dao.OrderHotelDao;
import petProject.spring.dao.RoomDao;
import petProject.spring.dto.OrderHotelDTO;
import petProject.spring.dto.OrderHotelRequest;
import petProject.spring.persistance.OrderHotel;
import petProject.spring.persistance.Room;
import petProject.spring.service.OrderHotelService;
import petProject.spring.utils.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderHotelServiceImpl implements OrderHotelService {

	private final RoomDao roomDao;
	private final OrderHotelDao orderHotelDao;

	@Override
	@Transactional
	public OrderHotelDTO createRequest(OrderHotelRequest request) {
		Room room = roomDao.findByType(request.getType())
				.orElseThrow(() -> new ApiException("Нет комнат с таким типом"));

		boolean isAvailable = roomDao.isAvailable(room.getId(), request.getCount());

		if (!isAvailable) {
			throw new ApiException("По вашему запросу нет доступных комнат");
		}

		roomDao.reserveRoom(
				room.getId(),
				(room.getCount() - request.getCount())
		);

		OrderHotelDTO orderHotelDTO = orderHotelDao.create(
				new OrderHotel(
						UUID.randomUUID(),
						request.getDaysCount(),
						UUID.fromString("392f214c-1610-4f03-8c77-075e41d63b67"),
						room.getId(),
						room.getServicesId()
				)
		);
		return orderHotelDTO;
	}
}
