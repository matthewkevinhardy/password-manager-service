package to.uk.mkhardy.passwordmanager.service.exception;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
	
	private static final Logger LOG = LoggerFactory.getLogger(ControllerAdvice.class);
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e,HttpServletRequest request) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR,e,request);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(Exception e,HttpServletRequest request) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR,e,request);
	}
	
	private ResponseEntity<ErrorResponse> error(HttpStatus status, Exception e,HttpServletRequest request) {
		LOG.error("Exception: ",e);
		return ResponseEntity.status(status).body(
				new ErrorResponse(status, status.value()+"", e.getMessage(), request.getRequestURI(), LocalDateTime.now(ZoneOffset.UTC)));
	}
}