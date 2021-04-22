package com.mydemo.demo.CodeGenerator;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * mybatisPlus database配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class MyDataSource {

    /**
     * 数据库连接
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 连接驱动
     */
    private String driverClassName;

}
