package com.acruent.college.globalexceptionhandle;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException 
{

  @ExceptionHandler(NoRecordException.class)
  public ResponseEntity<ExceptionMessage> handleNoRecordException( NoRecordException noRecordException, WebRequest webRequest)
	{
		ExceptionMessage exception = new ExceptionMessage(new Date(), noRecordException.getMessage(), webRequest.getDescription(false));
	
		return new ResponseEntity<ExceptionMessage>(exception,HttpStatus.OK);
	}
  
  
  @ExceptionHandler(NoIdException.class)
  public ResponseEntity<ExceptionMessage> handleNoIdException( NoIdException noIdException, WebRequest webRequest)
  {
	  ExceptionMessage exception = new ExceptionMessage(new Date(),noIdException.getMessage() , webRequest.getDescription(false));
  
	  return new ResponseEntity<ExceptionMessage>(exception,HttpStatus.OK);
  }
}
