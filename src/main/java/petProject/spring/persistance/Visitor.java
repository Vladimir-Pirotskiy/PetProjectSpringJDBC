package petProject.spring.persistance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Visitor {

	@Builder.Default
	private UUID id = UUID.randomUUID();
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
