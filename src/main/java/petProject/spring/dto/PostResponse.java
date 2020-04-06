package petProject.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long id;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String text;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String title;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	private LocalDateTime dateTime;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String error;

	public PostResponse(String text, String title, LocalDateTime dateTime, Long id) {
		this.text = text;
		this.title = title;
		this.dateTime = dateTime;
		this.id = id;
	}
}
