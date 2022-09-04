package com.aliano.configuration;

/**
 * @Author Cure
 * @Time 2022/5/19 16:54
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.aliano"))
                .build().apiInfo(new ApiInfoBuilder()
                        .title("curestore系统测试接口")
                        .description("Product商品管理")
                        .version("V1.0")
                        .build());
    }
}
