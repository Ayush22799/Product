package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
@ControllerAdvice
public class CustomExceptionHandler {
 @ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleFileNotFoundException(ResourceNotFoundException ex, WebRequest request){
	 Map<String, Object> body = new LinkedHashMap<>();
	 body.put("timestamp", LocalDateTime.now());
	 body.put("message", "File Not Found");
	 System.out.println("Error Message "+ ex.getMessage()+"Status code "+ HttpStatus.BAD_REQUEST);
	 ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
	 
	 return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
 }
}
