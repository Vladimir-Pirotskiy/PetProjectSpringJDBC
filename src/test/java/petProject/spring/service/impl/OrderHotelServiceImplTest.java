package petProject.spring.service.impl;

import petProject.spring.dao.OrderHotelDao;
import petProject.spring.dao.RoomDao;
import petProject.spring.dto.OrderHotelDTO;
import petProject.spring.dto.OrderHotelRequest;
import petProject.spring.persistance.OrderHotel;
import petProject.spring.persistance.Room;
import petProject.spring.service.OrderHotelService;
import petProject.spring.utils.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(SpringExtension.class)
class OrderHotelServiceImplTest {

	@Mock
	private OrderHotelDao orderHotelDao;
	@Mock
	private RoomDao roomDao;

	private OrderHotelService service;

	@BeforeEach
	void setUp() {
		initMocks(this);

		service = new OrderHotelServiceImpl(
				roomDao, orderHotelDao
		);
	}

	@Test
	void createRequest() {
		Room room = new Room(UUID.randomUUID(), "t", -1_000_000, BigDecimal.ONE, UUID.randomUUID());

		doReturn(Optional.of(room)).when(roomDao).findByType(anyString());

		doReturn(true)
				.when(roomDao).isAvailable(any(UUID.class), anyInt());

		Mockito.doReturn(new OrderHotelDTO(
				UUID.randomUUID(),
				1, "v", "double", null
		)).when(orderHotelDao).create(any(OrderHotel.class));

		OrderHotelRequest request = new OrderHotelRequest("double", 10, 1);
		OrderHotelDTO actual = service.createRequest(request);

		assertEquals(request.getType(), actual.getRoomType());

	}

	@Test
	void testThatRoomNotFound() {
		doReturn(Optional.empty())
				.when(roomDao).findByType(anyString());

		ApiException exception = assertThrows(
				ApiException.class,
				() -> service.createRequest(new OrderHotelRequest("t", 0, 0))
		);

		assertEquals("Нет комнат с таким типом", exception.getReason());
	}

	@Test
	void testThatRoomsAreNotAvailable() {
		doReturn(Optional.of(new Room()))
				.when(roomDao).findByType(anyString());

		doReturn(false)
				.when(roomDao).isAvailable(any(UUID.class), anyInt());

		ApiException exception = assertThrows(
				ApiException.class,
				() -> service.createRequest(new OrderHotelRequest("t", 0, 0))
		);

		assertEquals("По вашему запросу нет доступных комнат", exception.getReason());
	}
}
