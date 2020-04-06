package petProject.spring.service;

import petProject.spring.dto.TransactionDTO;

import java.util.List;

public interface TransactionsService {

	List<TransactionDTO> findAll();
}
