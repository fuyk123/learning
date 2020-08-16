package com.fuyk.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


    @Configuration
    @EnableSwagger2
    public class Swagger extends WebMvcConfigurationSupport {
        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    //为当前包路径
                    .apis(RequestHandlerSelectors.basePackage("com.fuyk.demo.controller"))
                    .paths(PathSelectors.any())
                    .build();
//        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
        }

        //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    //页面标题
                    .title("Spring Boot 使用 Swagger2 构建RESTful API")
                    //创建人
                    .contact(new Contact("Bryan", "http://blog.bianxh.top/", ""))
                    //版本号
                    .version("1.0")
                    //描述
                    .description("API 描述")
                    .build();
        }
        /**
         * 防止@EnableMvc把默认的静态资源路径覆盖了，手动设置的方式
         *
         * @param registry
         */
        @Override
        protected void addResourceHandlers(ResourceHandlerRegistry registry) {
            // 解决静态资源无法访问
            registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
            // 解决swagger无法访问
            registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            // 解决swagger的js文件无法访问
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        }
    }

