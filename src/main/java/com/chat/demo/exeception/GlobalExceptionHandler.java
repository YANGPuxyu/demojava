package com.chat.demo.exeception;

import com.chat.demo.response.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response<String> handleException(Exception e) {
        // 记录异常日志或做其他处理
        return Response.error("Internal server error: " + e.getMessage());
    }

}
