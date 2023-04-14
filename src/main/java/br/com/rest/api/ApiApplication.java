package br.com.rest.api;

import br.com.rest.api.domain.Project;
import br.com.rest.api.repository.ProjectRepository;
import br.com.rest.api.service.ProjectService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(ProjectRepository projectRepository) {
		return args -> {
			final Project project = new Project("A Incrible Project", "Robert Ipsum");
			projectRepository.save(project);
		};
	}

}
