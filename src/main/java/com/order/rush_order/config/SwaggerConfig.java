package com.order.rush_order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Rush Order API")
                        .version("1.0")
                        .description("이 문서는 Rush Order 프로젝트의 API에 대한 문서입니다."));
    }
}
