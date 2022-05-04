package cybersoft.java16.ecom.common.configure;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	public static final String AUTHORIZATION_HEADER = "Authorization";

	private ApiKey apiKey() { 
	    return new ApiKey("JWT", "Authorization", "header"); 
	}
	
	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.securityContexts(List.of(getSecurityContext()))
				.securitySchemes(List.of(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("cybersoft.java16.ecom"))
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Ecommer project")
				.version("1.0.0")
				.description("This project for practicing fullstack developer")
				.build();
	}
	private SecurityContext  getSecurityContext() {
		return SecurityContext.builder().securityReferences(List.of(getSecurityReferences())).build();
	}

	private SecurityReference getSecurityReferences() {
		AuthorizationScope scope = new AuthorizationScope("global","accept all request");
		AuthorizationScope[] authorizationScopes= new AuthorizationScope[1];
		authorizationScopes[0]=scope;
		return SecurityReference.builder().scopes(authorizationScopes).reference("JWT").build();
	}
}
