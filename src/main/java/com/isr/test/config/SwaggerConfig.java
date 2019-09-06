package com.isr.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * It will generate the SwaggerIU
     * @return
     */
    @Bean
    public Docket loginApi() {
        ApiInfo apiInfo = new ApiInfoBuilder().title("Login API").build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .groupName("Login-API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.isr.test.web.rest.controller"))
                .build();
    }

}
