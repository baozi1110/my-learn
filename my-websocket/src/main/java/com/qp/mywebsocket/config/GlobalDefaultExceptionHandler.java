// package com.qp.mywebsocket.config;
//
// import javax.servlet.http.HttpServletRequest;
//
// import com.qp.mywebsocket.util.ApiReturnObject;
// import com.qp.mywebsocket.util.ApiReturnUtil;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseBody;
//
//
// @ControllerAdvice
// public class GlobalDefaultExceptionHandler {
//
//     @ExceptionHandler(Exception.class)
//     @ResponseBody
//     public ApiReturnObject defaultExceptionHandler(HttpServletRequest req, Exception e) {
//         e.printStackTrace();
//         //return new ApiReturnObject("01","server error", e.getMessage());
//         return ApiReturnUtil.error("服务器异常",e.getMessage());
//     }
//
// }
