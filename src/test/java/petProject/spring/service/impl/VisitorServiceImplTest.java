package petProject.spring.service.impl;

import petProject.spring.dao.VisitorDao;
import petProject.spring.persistance.Visitor;
import petProject.spring.service.VisitorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

class VisitorServiceImplTest {

	@Mock
	private VisitorDao visitorDao;

	private VisitorService service;

	@BeforeEach
	void setUp() {
		initMocks(this);

		service = new VisitorServiceImpl(visitorDao);
	}

	@Test
	void register() {
		Visitor visitor = Visitor.builder()
				.email("ema")
				.firstName("f")
				.lastName("l")
				.password("p")
				.build();

		doReturn(visitor)
				.when(visitorDao).create(any(Visitor.class));

		Visitor actual = service.register(visitor);

		assertEquals(visitor, actual);

	}
}
