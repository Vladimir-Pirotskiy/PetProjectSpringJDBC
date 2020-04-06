package petProject.spring.controller;

import petProject.spring.dto.TransactionDTO;
import petProject.spring.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionsController {

	private final TransactionsService transactionsService;

	@GetMapping
	public Collection<TransactionDTO> findAll() {
		return transactionsService.findAll();
	}

}
