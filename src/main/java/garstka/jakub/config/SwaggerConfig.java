package garstka.jakub.config;

import garstka.jakub.controllers.ConversionController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static garstka.jakub.config.Constants.CONVERSION_CONTROLLER_TAG;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docker() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/api/v1/.*"))
                .build()
                .pathMapping("/")
                .tags(new Tag(CONVERSION_CONTROLLER_TAG, "Enables manipulation of a number numeral system"))
                .apiInfo(metadata());
    }

    private ApiInfo metadata() {
        Contact contact = new Contact("Jakub Garstka", null, "jgarstka3@gmail.com");

        return new ApiInfo(
                "NumberConverter - recruitment task COMARCH",
                "Service enables converting numbers in the arabic numeral system into romain or hexadecimal representation",
                "1.0",
                null,
                contact,
                null,
                null,
                new ArrayList<>());
    }

}
