package pt.com.visualnuts.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseBody
public class RestErrorHandler {

	  @ExceptionHandler(IllegalArgumentException.class)
	  public ResponseEntity<ExceptionMessage> resourceNotFoundException(IllegalArgumentException ex, WebRequest request) {
		  return  ResponseEntity.badRequest().body(new ExceptionMessage(400, "Parametro incorreto por favor informe um valido"));
	  }

}
