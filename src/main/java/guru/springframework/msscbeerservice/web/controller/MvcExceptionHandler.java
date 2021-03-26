package guru.springframework.msscbeerservice.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 2019-05-25.
 */
@ControllerAdvice
public class MvcExceptionHandler {

    // could return many errors to client
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException ex){
        List<String> errorsList = new ArrayList<>(ex.getConstraintViolations().size());
        // you could create a formal errors object,
        // spring may introduce a formal handling of bean validation errors to send as a rest response 
        ex.getConstraintViolations().forEach(error -> errorsList.add(error.toString())); 

        return new ResponseEntity<>(errorsList, HttpStatus.BAD_REQUEST);
    }

}
