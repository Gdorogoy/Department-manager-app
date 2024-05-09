package web.app.es;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@OpenAPIDefinition(
		info=@Info(
				title = "Employee service REST APIs ",
				description = "Employee service REST APIs for Employee management web-app final project ",
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
				description = "Employee service REST APIs docs",
				url = "https://github.com/Gdorogoy"

		)
)
@SpringBootApplication
@EnableFeignClients
public class EmployeeServiceApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}



	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
