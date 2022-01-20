package com.bruno.os.services.exceptions;

//extendendo da classe de erros e exeções
public class DataIntegratyViolationException extends RuntimeException{
private static final long serialVersionUID = 1L;


public DataIntegratyViolationException(String message, Throwable cause) {
	super(message, cause);
	
}

public DataIntegratyViolationException(String message) {
	super(message);
	
}
}