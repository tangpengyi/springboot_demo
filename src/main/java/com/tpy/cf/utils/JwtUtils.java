package com.tpy.cf.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;

public class JwtUtils {

    private static String SECERT = "123456123456123456";

    /**
     * java-jwt  生成token
     * @return
     */
    public static String getJavaToken(Integer userId){

        Calendar instance = Calendar.getInstance();

        //设置过期时间
        instance.add(Calendar.SECOND,6000);

        return JWT.create()
                .withIssuer("发送人")
                .withSubject("subject")
                .withClaim("userId", userId)
                //制定过期时间
                .withExpiresAt(instance.getTime())
                //签名
                .sign(Algorithm.HMAC256(SECERT));
    }

    /**
     * 验证token
     * @param token
     */
    public static DecodedJWT verity(String token){
        return JWT.require(Algorithm.HMAC256(SECERT)).build().verify(token);
    }

    public static Integer getUserId(String token){
        return JWT.require(Algorithm.HMAC256(SECERT))
                .build()
                .verify(token)
                .getClaim("userId")
                .asInt();
    }

}
