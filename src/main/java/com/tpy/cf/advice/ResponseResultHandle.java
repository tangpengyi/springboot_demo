package com.tpy.cf.advice;

import com.tpy.cf.annototion.ResponseResult;
import com.tpy.cf.api.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 拦截相应结果
 */
@ControllerAdvice(basePackages = "com.tpy.cf.controller")
@Slf4j
public class ResponseResultHandle implements ResponseBodyAdvice<Object> {
    private static final String RESPONSE_RESULT_ANN = "RESPONSE_RESULT_ANN";



    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    //结果封装
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        // 判断是否添加包装标记
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        // 判断请求 是否有包标记
        ResponseResult attribute = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);
        if(attribute == null){
            return o;
        }

        // 如果是Result 说明这个是异常类，直接返回
        if(o instanceof Result){
            return o;
        }
        return Result.success(o);
    }

}
