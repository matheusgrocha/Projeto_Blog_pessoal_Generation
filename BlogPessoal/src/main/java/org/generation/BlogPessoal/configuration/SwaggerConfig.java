package org.generation.BlogPessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Blog Pessoal").description("Project Blog Pessoal").version("v0.0.1")
						.license(new License().name("Blog Pessoal").url("https://brazil.genetation.org/"))
						.contact(new Contact().name("Github Matheus Gomes").url("https://github.com/Rorschach616")
								.email("mgrspeed@gmail.com")))
				.externalDocs(new ExternalDocumentation().description("Github Project")
						.url("https://github.com/Rorschach616/Projeto_Blog_pessoal_Generation.git"));
	}

	private ApiResponse createApiResponse(String message) {
		return new ApiResponse().description(message);
	}

	@Bean
	public OpenApiCustomiser customerGlobalResponseStatus() {
		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
				ApiResponses api = operation.getResponses();

				api.addApiResponse("200", createApiResponse("Sucess!"));
				api.addApiResponse("201", createApiResponse("Created!"));
				api.addApiResponse("400", createApiResponse("Request error!"));
				api.addApiResponse("401", createApiResponse("Not authorized!"));
				api.addApiResponse("500", createApiResponse("Internal server Error!"));
			}));
		};
	}

}