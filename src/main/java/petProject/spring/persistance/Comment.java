package petProject.spring.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

	private String textComment;
	private LocalDateTime date;
	private Long authorId;
	private Long postId;
}
