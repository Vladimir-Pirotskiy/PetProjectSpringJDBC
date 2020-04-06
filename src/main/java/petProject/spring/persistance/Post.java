package petProject.spring.persistance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

	private Long id;
	private String title;
	private String textNews;
	private LocalDateTime date;
	private Author author;

}
