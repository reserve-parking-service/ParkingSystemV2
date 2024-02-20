package co.kr.pms.common.swagger;

import co.kr.pms.common.util.ConstantsString;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import java.util.Arrays;


@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(ConstantsString.AUTH_CONTROLLER_PREFIX)).build();
    }

    @Bean
    public OpenAPI openAPI(){
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme(ConstantsString.BEARER_TOKEN_SMALL_PREFIX).bearerFormat(ConstantsString.AUTH_BEARER_JWT_PREFIX)
                .in(SecurityScheme.In.HEADER).name(ConstantsString.AUTH_AUTHORIZATION_PREFIX);
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(ConstantsString.AUTH_BEARER_AUTH_TOKEN_PREFIX);

        return new OpenAPI()
                .info(new Info().title("Parking Management System").description("주차관리 시스템을 관리").version("1.0.0"))
                .components(new Components().addSecuritySchemes(ConstantsString.AUTH_BEARER_TOKEN_KEY_PREFIX, securityScheme))
                .security(Arrays.asList(securityRequirement));
    }

}
