package starter.controller;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import starter.exception.BusinessException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BusinessException.class)
	public String handleBizException(Exception e, Model model) {
		model.addAttribute("type", e.getClass().getSimpleName());
		model.addAttribute("msg", e.getMessage());
		return "error";
	}
	
	@ExceptionHandler(MyBatisSystemException.class)
	public String handleMyBatisSystemException(Exception e, Model model) {
		model.addAttribute("type", e.getClass().getSimpleName());
		model.addAttribute("msg", e.getMessage());
		return "error";
	}
}