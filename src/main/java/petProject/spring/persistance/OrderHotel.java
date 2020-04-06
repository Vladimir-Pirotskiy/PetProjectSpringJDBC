package petProject.spring.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHotel {

	private UUID id;
	private int datesCount;
	private UUID visitorId;
	private UUID roomId;
	private UUID servicesId;
}
