package petProject.spring.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {

	private Long id;
	private BigDecimal amount;
	private LocalDate date;
	private String familyType;
	private Long incomeId;
	private Long outcomeId;
}
