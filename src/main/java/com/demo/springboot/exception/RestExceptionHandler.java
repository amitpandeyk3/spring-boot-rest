package com.demo.springboot.exception;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demo.springboot.dto.ErrorDetail;
import com.demo.springboot.dto.ErrorDetail.ValidationError;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler
	public ResponseEntity<?> handleResponseEntityException(ResourceNotFoundException rnfe){
		ErrorDetail errorDetail = createErrorDetail(HttpStatus.NOT_FOUND , rnfe, "Resource Not Found");
		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleValidateError(MethodArgumentNotValidException manve){
		ErrorDetail errorDetail= createErrorDetail(HttpStatus.BAD_REQUEST, manve, "Validation failed");
		List<FieldError> fieldErrors =  manve.getBindingResult().getFieldErrors();
		for(FieldError fieldError: fieldErrors){
			List<ValidationError> validationErrorList = errorDetail.getErrors().get(fieldError.getField());
            if(validationErrorList == null) {
                    validationErrorList = new ArrayList<ValidationError>();
                    errorDetail.getErrors().put(fieldError.getField(), validationErrorList);
            }
            ValidationError validationError = new ValidationError(fieldError.getCode(), messageSource.getMessage(fieldError, null));           
            validationErrorList.add(validationError);
		}
		return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
		
	}
	

	private ErrorDetail createErrorDetail(HttpStatus httpStatus, Exception ex, String title) {
		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setStatus(httpStatus.value());
		Date date = new Date();
		errorDetail.setTimeStamp( new Timestamp(date.getTime()));
		errorDetail.setTitle(title);
		errorDetail.setDetail(ex.getMessage());
		errorDetail.setDeveloperMessage(ex.getClass().getName());
		return errorDetail;
	}

}
