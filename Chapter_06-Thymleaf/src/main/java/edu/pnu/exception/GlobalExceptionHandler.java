package edu.pnu.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BoardException.class)
	public String handleCustomExeption(BoardException exception, Model model) {
		model.addAttribute("exception", exception);
		return "/errors/boardError";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String handleCustomExeption1(IllegalArgumentException exception, Model model) {
		model.addAttribute("message", exception.getMessage());
		return "/errors/globalError";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleExeption(Exception exception, Model model) {
		model.addAttribute("message", exception.getMessage());
		return "/errors/globalError";
	}
}
