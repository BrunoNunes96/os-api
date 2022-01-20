package com.bruno.os.services.exceptions;

// extendendo da classe de erros e exeções
public class ObjectNotFoundException extends RuntimeException{
private static final long serialVersionUID = 1L;


public ObjectNotFoundException(String message, Throwable cause) {
	super(message, cause);
	
}

public ObjectNotFoundException(String message) {
	super(message);
	
}


	
}
