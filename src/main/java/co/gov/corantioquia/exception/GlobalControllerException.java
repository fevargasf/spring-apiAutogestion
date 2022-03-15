package co.gov.corantioquia.exception;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerException extends ResponseEntityExceptionHandler {

    //@ExceptionHandler(NotFoundException.class)

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {

        String msg = exception.toString();
        StringBuilder sb = new StringBuilder();
        int init = 0;
        int end = 0;
        if (msg.length() > 2000) {
            end = 1160;
            sb.append(msg.substring(0, end));
            sb.append("                            ......................");
            init = msg.length() - 1900;
            sb.append(msg.substring(init, msg.length()));
        } else {
            sb.append(msg);
        }
        msg = sb.toString() + "  .......................................................";

        Map<String, String> map = new HashMap<>();
        map.put("error", exception.getClass().getCanonicalName());
        map.put("message", msg);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
