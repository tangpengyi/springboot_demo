package com.tpy.cf.advice;

import com.tpy.cf.api.Result;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 异常同一管理类
 */
@ControllerAdvice
public class GlobalExceptionResolver {

    // 校验参数异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result validationError(MethodArgumentNotValidException ex) {

        FieldError fieldError = ex.getBindingResult().getFieldError();
        String defaultMessage = fieldError.getField() + fieldError.getDefaultMessage();

        Result result = Result.failed(defaultMessage);
        return result;
    }

    @ResponseBody
    @ExceptionHandler(value = UnauthorizedException.class)//处理访问方法时权限不足问题
    public Result AuthcErrorHandler() throws IOException {
        return Result.failed("你没有此权限");
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)//处理访问方法时权限不足问题
    public Result AuthcErrorHandler(Exception e) throws IOException {
        return Result.failed(e.getMessage());
    }
}
