package com.mydemo.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author
 * @create 2020-04-13
 * @since 1.0.0
 */
@Component
public class DemoConfig {

    @Value("${my.ip}")
    public String ip;

    @Value("${my.port}")
    public String port;

    @Value("${server.servlet.context-path}")
    public String context_path;


    /**
     * psd加密方式
     */
    public static final String ENCRYPT_TYPE = "MD5";
    /**
     * psd加密次数
     */
    public static final Integer ENCRYPT_TIMES = 2;

    /**
     * jwt账户
     */
    public static final String JWT_ACCOUNT = "username";
    /**
     * jwt发布者
     */
    public static final String JWT_ISSUER = "myq";
    /**
     * jwt时间戳
     */
    public static final String JWT_CURRENT_TIMESTAMP = "currentTimestamp";
    /**
     * jwt refresh_token过期时间，单位s
     */
    public static final Integer JWT_REFRESH_TOKEN_EXPIRE_TIME = 2 * 60 * 60;
    /**
     * jwt token过期时间，单位s
     */
    public static final Integer JWT_TOKEN_EXPIRE_TIME = 2 * 60 * 60;
    /**
     * jwt 加密密钥
     */
    public static final String JWT_ENCRYPT_KEY = "bXlxSnd0U2hpcm8xMzc5MjA=";


    public static String EMAIL_SERVER = "163";//或者139，qq等邮箱服务器
    public static String SMTP_EMAIL = "smtp." + EMAIL_SERVER + ".com";
    public static String SEND_ADDRESS = "18222956710@163.com";
    public static String SEND_ACCOUNT = "18222956710";
    public static String SEND_PASSWORD = "MAYUEQIANG666888";

    /**
     * 26为大写字母
     */
    public final static String U_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXVZ";
    /**
     * 26位小写字母
     */
    public final static String L_CHARS = "abcdefghijklmnopqrstuvwxyz";

    /**
     * 十位数字
     */
    public final static String NUMS = "0123456789";




}