package petProject.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
	private Long id;
	private BigDecimal amount;
	@JsonFormat(pattern = "dd.MM.yyyy")
	private LocalDate date;
	private String familyMember;
	private String income;
	private String outcome;
}
