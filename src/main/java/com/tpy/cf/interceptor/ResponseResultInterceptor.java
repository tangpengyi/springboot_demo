package com.tpy.cf.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.tpy.cf.annototion.ResponseResult;
import com.tpy.cf.config.MyRealm;
import com.tpy.cf.service.UserService;
import com.tpy.cf.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
@Slf4j
public class ResponseResultInterceptor implements HandlerInterceptor {

    private final String RESPONSE_RESULT_ANN = "RESPONSE_RESULT_ANN";

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("访问地址==="+request.getRequestURI());

        // swagger 验证
        if (javaJwt(request) == false) {
            throw new Exception("用户未登录");
        }

        log.info(request.getHeader("token"));

        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;

            Class<?> clazz = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();

            if (clazz.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(RESPONSE_RESULT_ANN, clazz.getAnnotation(ResponseResult.class));
            } else if (method.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(RESPONSE_RESULT_ANN, method.getAnnotation(ResponseResult.class));
            }
        }
        // true 放行 false  不放行
        return true;
    }

    //java-jwt Header 验证
    public boolean javaJwt(HttpServletRequest request) {
        String token = request.getHeader("token");
        Integer userId = 0;
        if (StringUtils.isEmpty(token))
            return false;
        try {
            userId = JwtUtils.getUserId(token);
        } catch (Exception e) {
            return false;
        }

        MyRealm.setToken(token);
        userService.login(userId);
        return true;
    }
}
