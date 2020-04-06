package petProject.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
	private String type;
	private Integer count;
	private BigDecimal cost;

	private boolean three_b;
	private boolean ski;
	private boolean skiPass;
}
