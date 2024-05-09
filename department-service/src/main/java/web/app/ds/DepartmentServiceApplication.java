package web.app.ds;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@OpenAPIDefinition(
		info=@Info(
				title = "Department service REST APIs ",
				description = "Department service REST APIs for Employee management web-app final project ",
				version = "1.0",
				contact = @Contact(
						name = "Georgy Dorogoy",
						email = "gdorogoy@gmail.com",
						url = "https://github.com/Gdorogoy"
				),
				license=@License(
					name = "Apache/2.4.56 (Unix)",
					url = "https://github.com/Gdorogoy"

				)
		),
		externalDocs=@ExternalDocumentation(
				description = "Department service REST APIs docs",
				url = "https://github.com/Gdorogoy"

		)
)
@SpringBootApplication
@EnableFeignClients
public class DepartmentServiceApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

}
