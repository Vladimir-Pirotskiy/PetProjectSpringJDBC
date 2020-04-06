package petProject.spring.service.impl;

import petProject.spring.dao.RoomDao;
import petProject.spring.dto.RoomDTO;
import petProject.spring.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

	private final RoomDao roomDao;

	@Override
	public List<RoomDTO> getAll() {
		return roomDao.findAll();
	}
}
