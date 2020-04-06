package petProject.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHotelRequest {

	private String type;
	private Integer count;
	private Integer daysCount;
}
