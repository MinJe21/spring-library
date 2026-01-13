package spring.library.util;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class  SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Library API")
                .version("1.0.0")
                .description("Library API Documentation");

        Server prodServer = new Server()
                .url("http://localhost:8080")
                .description("Production server");

        return new OpenAPI()
                .info(info)
                .servers(List.of(prodServer));
    }
}

