package gr.hua.dit.ds.restdemo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/* A controller that is responsible to handle some error that may occur at handling requests 
 * Can manage null pointers,json problems,and non existing attributes
 * if server error then returns internal error with a message
 * */


@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handle(Exception ex) {
		if (ex instanceof NullPointerException) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(ex instanceof HttpMessageNotReadableException) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Json parsing encountered an error");
		}
		if(ex instanceof AttributeNotFoundException) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attribute not found");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The server encountered an error");
	}
}
