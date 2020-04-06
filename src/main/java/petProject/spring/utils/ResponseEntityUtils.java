package petProject.spring.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;

import java.util.List;

@UtilityClass
public final class ResponseEntityUtils {

	public static <T> ResponseEntity<List<T>> okIfNotEmpty(List<T> collection) {
		return collection.isEmpty()
				? ResponseEntity.badRequest().build()
				: ResponseEntity.ok(collection);
	}
}
