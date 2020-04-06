package petProject.spring.dao;

import petProject.spring.dto.RoomDTO;
import petProject.spring.persistance.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomDao {

	List<RoomDTO> findAll();

	Optional<Room> findByType(String type);

	boolean isAvailable(UUID id, int count);

	boolean reserveRoom(UUID id, int count);

}
