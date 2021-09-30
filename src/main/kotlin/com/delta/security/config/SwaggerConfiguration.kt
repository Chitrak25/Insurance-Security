package com.delta.security.config

import com.google.common.base.Predicates
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfiguration : WebMvcConfigurationSupport() {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
            .build()
            .apiInfo(metaInfo())
    }

    private fun metaInfo(): ApiInfo {
        return ApiInfo(
            "Employee Management",
            "This API provides information with respect to employee related information",
            "1.0",
            "Terms of Service",
            Contact("", "", ""),
            "", ""
        )
    }

    public override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/")
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }
}