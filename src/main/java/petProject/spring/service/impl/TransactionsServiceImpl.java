package petProject.spring.service.impl;

import petProject.spring.dao.TransactionsDao;
import petProject.spring.dto.TransactionDTO;
import petProject.spring.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsServiceImpl implements TransactionsService {

	private final TransactionsDao transactionsDao;

	@Override
	public List<TransactionDTO> findAll() {
		return transactionsDao.findAll();
	}
}
