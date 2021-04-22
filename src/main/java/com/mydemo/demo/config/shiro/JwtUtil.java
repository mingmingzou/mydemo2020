package com.mydemo.demo.config.shiro;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mydemo.demo.common.Exception.ServerException;
import com.mydemo.demo.config.DemoConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT token 工具类,提供JWT生成,校验,工作
 *
 * @Date 2019-05-25
 * @Description: TODO
 */
@Component
@Slf4j
public class JwtUtil {


    public static void main(String[] args) {
        String decode = Base64.decodeStr("IwAAANx4hrjy0g==");
        System.out.println(decode);

        String encode = Base64.encode("lankeAndroid2020");
        System.out.println(encode);
    }

    /**
     *
     * 生成JWT token
     * @return
     */
    public static String sign(String username, String currentTimestamp) {
        Date expireDate = DateUtil.offsetMillisecond(new Date(), DemoConfig.JWT_TOKEN_EXPIRE_TIME * 1000);
        String secret = username + DemoConfig.JWT_ENCRYPT_KEY;

        Map<String, Object> header = new HashMap<>();
        header.put("type", "JWT");
        return JWT.create()
                .withHeader(header)
                .withClaim(DemoConfig.JWT_ACCOUNT, username)
                .withClaim(DemoConfig.JWT_CURRENT_TIMESTAMP, currentTimestamp)
                .withExpiresAt(expireDate)
                .withIssuer(DemoConfig.JWT_ISSUER)
                .sign(Algorithm.HMAC256(secret));
    }


    /**
     * 获取用户名
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        return getClaim(token, DemoConfig.JWT_ACCOUNT);
    }


    /**
     * 获得Token中的信息无需secret解密也能获得
     * @param token
     * @param claim
     * @return
     */
    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            // 只能输出String类型，如果是其他类型返回null
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            log.error("解密Token中的公共信息出现JWTDecodeException异常:{}", e.getMessage());
            throw new ServerException(-1, "解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
        }
    }


    /**
     * token是否合法
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        String secret = getUsername(token) + DemoConfig.JWT_ENCRYPT_KEY;
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
        try {
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("JWTToken认证解密出现UnsupportedEncodingException异常:{}", e.getMessage());
            return false;
        }
    }


    /**
     * 检查token是否过期
     * @param token
     * @return
     */
    public static boolean isExpired(String token) {
        return DateUtil.compare(JWT.decode(token).getExpiresAt(), new Date()) < 0 ? true : false;
    }


    /**
     * 检查token中的时间戳和redis中的时间戳是否一致
     * @param token
     * @param currentTimestamp
     * @return
     */
    public static boolean isRefreshExpired(String token, String currentTimestamp) {
        return getClaim(token, DemoConfig.JWT_CURRENT_TIMESTAMP).equals(currentTimestamp);
    }

}