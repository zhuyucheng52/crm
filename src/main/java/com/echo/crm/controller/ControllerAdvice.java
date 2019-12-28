package com.echo.crm.controller;

import com.echo.crm.utils.ResultInfo;
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
public class ControllerAdvice {
	@ExceptionHandler({ Exception.class })
	@ResponseBody
	public ResultInfo<Object> handleException(Exception e) {
		log.warn("Controller process error!", e);
		return new ResultInfo(ResultInfo.Result.RESULT_FAILURE, e.getMessage());
	}
}
