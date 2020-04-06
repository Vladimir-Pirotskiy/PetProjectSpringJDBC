package petProject.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForSaleDTO {

	private String category;
	private String name;
	private boolean isAvailable;
}
