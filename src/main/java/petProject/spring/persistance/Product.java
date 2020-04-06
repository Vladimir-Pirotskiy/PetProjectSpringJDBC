package petProject.spring.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	private Long id;
	private String name;
	private int count;
	private String supplier;
	private Long categoryId;
}
