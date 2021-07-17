package com.cit.projetoPratico.swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.cit.projetoPratico"))
          .paths(PathSelectors.any())
          .build()
//          .useDefaultResponseMessages(false)  SERÁ NECESSÁRIO CONFIGURAR A AUTENTICAÇÃO PARA O SWAGGER
//          .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
//          .securitySchemes(Arrays.asList(new ApiKey("Token Access", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
//          .securityContexts(Arrays.asList(securityContext()))
          ;
    }
}