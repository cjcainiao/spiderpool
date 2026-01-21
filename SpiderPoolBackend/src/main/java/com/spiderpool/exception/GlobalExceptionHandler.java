package com.spiderpool.exception;


import com.spiderpool.common.utils.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 运行时异常处理（最后兜底）
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult handleRuntimeException(RuntimeException e) {
        return ResponseResult.error(e.getMessage());
    }


    /**
     * 业务异常处理
     * @param e
     * @return
     */

    @ExceptionHandler(BusinessException.class)
    public ResponseResult handleBusinessException(BusinessException e) {
        return ResponseResult.error(e.getCode(),e.getMessage());
    }
}