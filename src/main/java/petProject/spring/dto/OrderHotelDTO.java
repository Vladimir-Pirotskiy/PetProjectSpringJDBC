package petProject.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHotelDTO {

	private UUID id;
	private int datesCount;
	private String visitorEmail;
	private String roomType;
	private UUID services;

}
