package com.luv2code.springboot.todos.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.context.annotation.Configuration;

// Add Authorization with a bearer token input in the Swagger UI
@Configuration
@OpenAPIDefinition(
        info=@Info(title="API Documentation", version="v1"),
        security = @SecurityRequirement(name="bearerAuth")
)
@SecurityScheme(
        name="bearerAuth",
        type= SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
    // Leave this blank

}
