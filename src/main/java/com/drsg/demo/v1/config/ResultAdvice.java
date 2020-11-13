package com.drsg.demo.v1.config;

import com.drsg.demo.v1.entity.Result;
import com.drsg.demo.v1.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResultAdvice {
    private final Logger logger = LoggerFactory.getLogger(ResultAdvice.class);

    @ExceptionHandler(BusinessException.class)
    public Result<?> businessExceptionHandle(BusinessException e) {
        logger.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result<?> exceptionHandle(AccessDeniedException e) {
        logger.error(e.getMessage());
        return Result.fail("访问被拒绝");
    }

    @ExceptionHandler(Exception.class)
    public Result<?> exceptionHandle(Exception e) {
        logger.error(e.getMessage(), e);
        return Result.fail("服务器错误");
    }
}
