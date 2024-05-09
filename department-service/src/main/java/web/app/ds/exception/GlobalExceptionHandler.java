package web.app.ds.exception;


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
                "DEPARTMENT_NOT_FOUND"
        );

        return  new ResponseEntity<>(errorDeatails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CodeAlreadyExistsException.class)
    public ResponseEntity<ErrorDeatails> handleEmailAlreadyExistsException(
            CodeAlreadyExistsException exception,
            WebRequest webRequest
    ){
        ErrorDeatails errorDeatails=new ErrorDeatails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "CODE_ALREADY_EXISTS"
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
