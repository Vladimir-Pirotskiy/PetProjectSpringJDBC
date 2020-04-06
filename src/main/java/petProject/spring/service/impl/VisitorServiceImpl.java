package petProject.spring.service.impl;

import petProject.spring.dao.VisitorDao;
import petProject.spring.persistance.Visitor;
import petProject.spring.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService {

	private final VisitorDao visitorDao;

	@Override
	public Visitor register(Visitor visitor) {
		return visitorDao.create(visitor);
	}

}
