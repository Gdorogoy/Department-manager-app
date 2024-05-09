package web.app.es.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDeatails> handleResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest webRequest
    ){
        ErrorDeatails errorDeatails=new ErrorDeatails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );

        return  new ResponseEntity<>(errorDeatails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDeatails> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException exception,
            WebRequest webRequest
    ){
        ErrorDeatails errorDeatails=new ErrorDeatails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_EMAIL_ALREADY_IN_USE"
        );
        return  new ResponseEntity<>(errorDeatails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDeatails> handleGlobalException(
            Exception exception,
            WebRequest webRequest
    ){
        ErrorDeatails errorDeatails=new ErrorDeatails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL SERVER ERROR"
        );

        return  new ResponseEntity<>(errorDeatails, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
