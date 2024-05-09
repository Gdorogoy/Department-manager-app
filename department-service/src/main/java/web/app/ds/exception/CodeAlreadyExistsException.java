package web.app.ds.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CodeAlreadyExistsException extends RuntimeException{
    private String message;


    public CodeAlreadyExistsException(String message){
        super(message);
    }
}
