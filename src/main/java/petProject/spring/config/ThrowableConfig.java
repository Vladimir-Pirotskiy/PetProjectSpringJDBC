package petProject.spring.config;

import petProject.spring.dto.SimpleResponse;
import petProject.spring.utils.ApiException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Configuration
@ControllerAdvice
@ResponseBody
public class ThrowableConfig {

	@ExceptionHandler(ApiException.class)
	public SimpleResponse handleException(ApiException ex, HttpServletResponse response) {
		response.setStatus(ex.getStatus().value());
		return new SimpleResponse(ex.getReason());
	}

}
