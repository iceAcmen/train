package com.maxrocky.train.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Ice
 * @version 1.0
 * @date 2018/3/21 17:54
 * @desc TODO
 */
@EnableSwagger2//swagger的开关，表示我们在项目中启用swagger
@Getter@Setter
@Configuration//声明这是一个配置类
@ConfigurationProperties(prefix = "swagger")//SpringBoot提供的属性自动赋值，也可以直接在属性上使用@value($("swagger.属性名"))
public class SwaggerConfiguration {

    //controller接口所在的包
    private String basePackage = "com.maxrocky.train.controller";

    //当前文档的标题
    private String title = "他很懒，什么都没有留下";

    //当前文档的详细描述
    private String description = "他很懒，什么都没有留下";

    //当前文档的版本
    private String version = "v1.0";


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .build();
    }

}
