package com.mydemo.demo.config.SwaggerConfig;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    //http://localhost/doc.html

    //配置说明
//    https://gitee.com/xiaoym/swagger-bootstrap-ui-demo/blob/master/swagger-bootstrap-ui-demo/src/main/java/com/swagger/bootstrap/ui/demo/config/SwaggerConfiguration.java
    @Bean(value = "defaultApi")
    public Docket defaultApi() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("我的demo接口文档")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.mydemo.demo.login.controller"))
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(HttpSession.class, HttpServletRequest.class, HttpServletResponse.class)//统一忽略方法上的class参数
                ;
        return docket;
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API 文档")
                .description("服务提供平台")
                .termsOfServiceUrl("http://localhost:8081/")
                .contact("zmm")
                .version("1.0")
                .build();
    }

}