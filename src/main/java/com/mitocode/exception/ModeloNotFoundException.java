package com.mitocode.exception;

//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;

/*@ResponseStatus(HttpStatus.NOT_FOUND) 
 *  YA NO ES NECESARIO XQ LA CLASE ResponseExceptionHandler 
 *  ES SUPERIOR Y UTILIZA @ExceptionHandler(ModeloNotFoundException.class)
 */
public class ModeloNotFoundException extends RuntimeException{
	
	public ModeloNotFoundException(String mensaje) {
		super(mensaje);
	}
	
}
