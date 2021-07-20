package br.edu.iff.projectLibrary.controller.apirest;

import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import br.edu.iff.projectLibrary.exception.Error;
import br.edu.iff.projectLibrary.exception.NotFoundException;
import br.edu.iff.projectLibrary.exception.PropertyError;
import br.edu.iff.projectLibrary.exception.ValidationError;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class MyRestControllerAdvice
{
    @ExceptionHandler(Exception.class)
    public ResponseEntity defaultError(Exception e, HttpServletRequest request)
    {
        Error error = new Error
        (Calendar.getInstance(), 
         HttpStatus.BAD_REQUEST.value(), 
         HttpStatus.BAD_REQUEST.name(),
         e.getMessage(), 
         request.getRequestURI());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity defaultError(NotFoundException e, HttpServletRequest request)
    {
        Error error = new Error
        (Calendar.getInstance(), 
         HttpStatus.NOT_FOUND.value(), 
         HttpStatus.NOT_FOUND.name(),
         e.getMessage(), 
         request.getRequestURI());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity validationError(ConstraintViolationException e, HttpServletRequest request)
    {
        ValidationError error = new ValidationError
        (Calendar.getInstance(), 
         HttpStatus.UNPROCESSABLE_ENTITY.value(), 
         HttpStatus.UNPROCESSABLE_ENTITY.name(),
         "Erro de Validação.", 
         request.getRequestURI());
        
        for(ConstraintViolation cv : e.getConstraintViolations())
        {
            PropertyError p = new PropertyError(cv.getPropertyPath().toString(), cv.getMessage());
            error.getErrors().add(p);
        }
        
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity validationError(MethodArgumentNotValidException e, HttpServletRequest request)
    {
        ValidationError error = new ValidationError
        (Calendar.getInstance(), 
         HttpStatus.UNPROCESSABLE_ENTITY.value(), 
         HttpStatus.UNPROCESSABLE_ENTITY.name(),
         "Erro de Validação.", 
         request.getRequestURI());
        
        for(FieldError fe : e.getBindingResult().getFieldErrors())
        {
            PropertyError p = new PropertyError(fe.getField(), fe.getDefaultMessage());
            error.getErrors().add(p);
        }
        
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }
    
}
