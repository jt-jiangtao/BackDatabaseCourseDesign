package com.jw.backdatabasecoursedesign.core;

import com.jw.backdatabasecoursedesign.exception.http.HttpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

// all exception even RunTimeException will be handled in here
@ControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public UnifyResponse handleMissingServletRequestParameterException(HttpServletRequest request,MissingServletRequestParameterException exception){
        return new UnifyResponse(1003, exception.getParameterName() + " 参数缺失");
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public UnifyResponse handleHttpMessageNotReadableException(HttpServletRequest request,HttpMessageNotReadableException exception){
        return new UnifyResponse(1004, exception.getRootCause().getLocalizedMessage().split("\n")[0]);
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(HttpServletRequest request,Exception exception) {
        log.error(exception.getStackTrace().toString());
        exception.printStackTrace();
        if (exception instanceof MethodArgumentTypeMismatchException) return new UnifyResponse(1006);
        return new UnifyResponse(1005);
    }

    @ExceptionHandler(value = HttpException.class)
    @ResponseBody
    public ResponseEntity<UnifyResponse> handleHttpException(HttpServletRequest request,HttpException exception){
        UnifyResponse unifyResponse = new UnifyResponse(exception.getCode(), exception.getData());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.resolve(exception.getHttpStatusCode());
        return new ResponseEntity<>(unifyResponse,headers,httpStatus);
    }
}
