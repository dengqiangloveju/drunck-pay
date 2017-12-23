package com.drunck.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drunck.utils.ResultInfo;

@ControllerAdvice
@ResponseBody
public class HandlerException {
	@ExceptionHandler({ ScheduleException.class })
	@ResponseStatus(HttpStatus.OK)
	public ResultInfo processException(ScheduleException e) {
		e.printStackTrace();
		return new ResultInfo(false, e.getMessage());
	}
	
	@ExceptionHandler({ BusinessException.class })
	@ResponseStatus(HttpStatus.OK)
	public ResultInfo processException(BusinessException e) {
		e.printStackTrace();
		return new ResultInfo(false, e.getMessage());
	}
	
	@ExceptionHandler({ RuntimeException.class })
	@ResponseStatus(HttpStatus.OK)
	public ResultInfo processException(RuntimeException e) {
		e.printStackTrace();
		return new ResultInfo(false, e.getMessage());
	}

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.OK)
	public ResultInfo processException(Exception e) {
		e.printStackTrace();
		return new ResultInfo(false, e.getMessage());
	}
}
