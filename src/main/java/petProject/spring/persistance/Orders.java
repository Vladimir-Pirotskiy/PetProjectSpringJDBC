package petProject.spring.persistance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
	public enum OrderStatus {
		CREATED, COMPLETED
	}

	private Long id;
	private OrderStatus status;
	private int count;
	private Long productId;
	private Long userId;

}
