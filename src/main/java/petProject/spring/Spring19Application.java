package petProject.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring19Application {

	// -Denv=123
	// java -jar name.jar -Denv=123
	public static void main(String[] args) {
		SpringApplication.run(Spring19Application.class, args);
	}

}
