package com.echo.crm.exception;

import com.echo.crm.dto.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 10:15
 */

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public ResultDTO<Object> handleException(Exception e) {
        log.warn("Controller process error!", e);
        return new ResultDTO(ResultDTO.Result.RESULT_FAILURE, e.getMessage());
    }
}
