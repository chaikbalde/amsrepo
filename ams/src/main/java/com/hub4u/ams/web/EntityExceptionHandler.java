/**
 * 
 */
package com.hub4u.ams.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author User
 *
 */
@ControllerAdvice 
public class EntityExceptionHandler {

	/**
	 * 
	 */
	public EntityExceptionHandler() {
	}
	
	/**
	 * 
	 * */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException manve) {
		Map<String, String> errors = new HashMap<String, String>();
		
		manve.getBindingResult().getAllErrors().forEach( error -> {
			String fieldName = ((FieldError)error).getField(); 
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		} );
		
		return errors;
	}

}
