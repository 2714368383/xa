package com.base.net.xa.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.base.net.xa.constant.AuthConstant;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @Auther: zhaikaixuan
 * @Date: 2019-08-24 11:45
 * @Description: Jwt签名工具类
 */
public class JwtUtils {

    private static String secret;
    private static Long expire_time;

    @Value("${jwt-securet}")
    public void setSecret(String secret) {
        JwtUtils.secret = secret;
    }

    @Value("${jwt-expire}")
    public void setExpire_time(Long expire_time) {
        JwtUtils.expire_time = expire_time;
    }

    // token签名
    public static String signJwt(String userId) {
        // 计算过期时间
        long currentTamp = System.currentTimeMillis() + expire_time;
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String sign = JWT.create()
                .withClaim(AuthConstant.USER_ID, userId)
                .withExpiresAt(new Date(currentTamp))
                .sign(algorithm);
        return sign;
    }

    /**
     * 签证签名
     *
     * @param sign
     * @return
     */
    public static boolean verfyCode(String sign) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT verify = verifier.verify(sign);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 从token中获取用户ID
     */
    public static String getUserId(String sign) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT verify = verifier.verify(sign);
        return verify.getClaim(AuthConstant.USER_ID).asString();
    }
}
