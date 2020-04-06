package petProject.spring.dao;

import petProject.spring.dto.TransactionDTO;

import java.util.List;

public interface TransactionsDao {

	List<TransactionDTO> findAll();
}
