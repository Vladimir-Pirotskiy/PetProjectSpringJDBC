package petProject.spring.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import petProject.spring.config.BigDecimalSerializer;
import petProject.spring.persistance.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

	private Orders.OrderStatus status;
	private int count;
	private String product;
	private String user;

	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal decimal;

}
