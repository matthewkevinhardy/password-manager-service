package to.uk.mkhardy.passwordmanager.service.exception;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class PassManControllerAdvice {
	
	private static final Logger LOG = LoggerFactory.getLogger(PassManControllerAdvice.class);
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e,HttpServletRequest request) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR,e,request);
	}
	
	@ExceptionHandler(PassManException.class)
	public ResponseEntity<ErrorResponse> handlePassManException(PassManException e,HttpServletRequest request) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR,e,request);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException e,HttpServletRequest request) {
		return error(e.getStatus(),e,request);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpServletRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp",LocalDateTime.now(ZoneOffset.UTC));
        body.put("status", HttpStatus.BAD_REQUEST);
        
		//Get all errors
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
		body.put("errors", errors);
		body.put("path",request.getRequestURI());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
	
	private ResponseEntity<ErrorResponse> error(HttpStatus status, Exception e,HttpServletRequest request) {
		LOG.error("Exception: ",e);
		return ResponseEntity.status(status).body(
				new ErrorResponse(status, status.value()+"", e.getMessage(), request.getRequestURI(), LocalDateTime.now(ZoneOffset.UTC)));
	}
}