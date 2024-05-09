package web.app.es.exception;

public class DepartmentNotFoundException extends RuntimeException{

    private final static String message="THE GIVEN DEPARTMENT CODE WAS NOT ASSIGNED TO ANY DEPARTMENT";
    public DepartmentNotFoundException(){
        super(message);
    }
}
