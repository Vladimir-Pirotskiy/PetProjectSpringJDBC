package petProject.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreationRequest {

	private String title;
	private String textNews;
	private String authorName;

}
