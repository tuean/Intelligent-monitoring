package com.sys.supervision.exception;

import com.sys.supervision.model.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        BaseResponse error = BaseResponse.error();
        String msg = ex.getMessage();
        ex.printStackTrace();
        error.setMessage(msg);
        if (msg != null) {
            return new ResponseEntity(BaseResponse.error(msg), HttpStatus.OK);
        }
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
