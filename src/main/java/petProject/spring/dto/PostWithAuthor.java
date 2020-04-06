package petProject.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostWithAuthor {

	private Long id;
	private String text;
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	private LocalDateTime dateTime;
	private String title;
	private String authorName;
	private String authorEmail;
}
