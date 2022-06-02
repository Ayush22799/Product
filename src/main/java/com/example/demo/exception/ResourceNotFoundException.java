package com.example.demo.exception;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{
	public  ResourceNotFoundException(String message){
		super(message);
	}
	public ResourceNotFoundException(String message, Throwable throwable){
		super(message);
	}

}
