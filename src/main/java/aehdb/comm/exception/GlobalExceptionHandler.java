package aehdb.comm.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> processValidationError(MethodArgumentNotValidException ex) {
		log.error("MethodArgumentNotValidException", ex);
		ErrorResponse response = new ErrorResponse(ErrorCode.BAD_REQUEST);
		BindingResult bindingResult = ex.getBindingResult();
		StringBuilder stringBuilder = new StringBuilder();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			stringBuilder.append(fieldError.getField()).append(":");
			stringBuilder.append(fieldError.getDefaultMessage());
			stringBuilder.append(", ");
		}
		response.setMessage(stringBuilder.toString());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex) {
		log.error("handleException", ex);
		ErrorResponse response = new ErrorResponse(ErrorCode.INTER_SERVER_ERROR);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex) {
		log.error("handleNoHandlerFoundException", ex);
		ErrorResponse response = new ErrorResponse(ErrorCode.NOT_FOUND);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AuthorizationServiceException.class)
	public ResponseEntity<ErrorResponse> handleAuthorizationServiceException(AuthorizationServiceException ex) {
		log.error("unAuthorized", ex);
		ErrorResponse response = new ErrorResponse(ErrorCode.UNAUTHORIZED);
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		log.error("DataIntegrityViolationException", ex.getRootCause());
		ErrorResponse response = new ErrorResponse(ErrorCode.INTER_SERVER_ERROR);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
