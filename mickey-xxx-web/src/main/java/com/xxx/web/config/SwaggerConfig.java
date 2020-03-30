package com.xxx.web.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author J·K
 * @Description: SwaggerConfiguration
 * @date 2019-11-20 10:36
 */
@Configuration
@EnableSwagger2
@Profile({"dev", "si"})
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        ParameterBuilder token = new ParameterBuilder();
        token.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType(
                "header").required(false);
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(token.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("mickey-framework-example")
                .contact(new Contact("J·K", "", ""))
                .version("1.0")
                .description("请求参数说明：<br/>" +
                        "1、timestamp，请求毫秒数，如：1574326112000<br/>" +
                        "2、signature，数据签名，签名规则：<br/>" +
                        "   1）参数字典排序<br/>" +
                        "   2）参数名+参数值<br/>" +
                        "   3）参数拼接字符串+'timestamp=1574326112000'+secret，取MD5值<br/>" +
                        "   4）例：{id:1,name:'张三',year:2019}，签名值：md5(id1name张三year2019timestamp=1574326112000bfa5a808f7ab4e01a235965182e30077)")
                .termsOfServiceUrl("www.mickey.com/")
                .build();
    }

}
