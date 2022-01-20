package com.bruno.os.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bruno.os.services.exceptions.DataIntegratyViolationException;
import com.bruno.os.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	// controlador da exceção
@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandarError>objectNotFoundException(ObjectNotFoundException e){
	
	StandarError error = new StandarError(System.currentTimeMillis(), 
	HttpStatus.NOT_FOUND.value(), e.getMessage());
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	
}

@ExceptionHandler(DataIntegratyViolationException.class)
public ResponseEntity<StandarError>objectNotFoundException(DataIntegratyViolationException e){
StandarError error = new StandarError(System.currentTimeMillis(), 
HttpStatus.BAD_REQUEST.value(), e.getMessage());

	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

}
	// controlador da exceção para campos de tecnico que estão em brancos
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<StandarError>objectNotFoundException(MethodArgumentNotValidException e){
ValidationError error = new ValidationError(System.currentTimeMillis(),
		HttpStatus.BAD_REQUEST.value(),"Erro na validação dos campos!");

for (FieldError x: e.getBindingResult().getFieldErrors()) {
	error.addErrors(x.getField(), x.getDefaultMessage());
}

return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

}
}
