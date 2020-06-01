package com.yaoshun.exception.handle;


import com.yaoshun.exception.BaseException;
import com.yaoshun.exception.errorEnum.BaseExceptionEnum;
import com.yaoshun.util.ExceptionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Iterator;
/**
 * @description: 全局异常处理器
 * @author: duanwei
 * @create: 2019-08-28 20:07
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({Exception.class})
    public ExceptionResult MethodArgumentNotValidHandler(Exception exception) throws Exception {
        ExceptionResult ExceptionResult = new ExceptionResult();
        HashMap fieldAndMessage;
        Iterator var5;
        FieldError fieldError;
        if (exception instanceof AccessDeniedException) {
            ExceptionResult.setCode(BaseExceptionEnum.ACCESSDENIED_ERROR.getCode());
            ExceptionResult.setMsg(BaseExceptionEnum.ACCESSDENIED_ERROR.getMsg());
        } else if (exception instanceof BindException) {
            fieldAndMessage = new HashMap();
            BindException bindException = (BindException) exception;
            var5 = bindException.getBindingResult().getFieldErrors().iterator();
            while (var5.hasNext()) {
                fieldError = (FieldError) var5.next();
                fieldAndMessage.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            ExceptionResult.setCode(BaseExceptionEnum.PARAMETER_ERROR.getCode());
            ExceptionResult.setMsg(BaseExceptionEnum.PARAMETER_ERROR.getMsg());
            ExceptionResult.setResult(fieldAndMessage);
        } else if (exception instanceof BaseException) {
            ExceptionResult.setCode(((BaseException) exception).getCode());
            ExceptionResult.setMsg(((BaseException) exception).getMsg());
        } else {
            ExceptionResult.setCode(BaseExceptionEnum.PARAMETER_ERROR.getCode());
            ExceptionResult.setMsg(BaseExceptionEnum.PARAMETER_ERROR.getMsg());
        }
        log.error(exception.getMessage(), exception);
        return ExceptionResult;
    }

}
