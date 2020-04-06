package petProject.spring.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
	private UUID id;
	private String type;
	private int count;
	private BigDecimal cost;
	private UUID servicesId;
}
