package net.buscacio.vacccinationApi.exceptionhandler;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(RessourceException.class)
    public ResponseEntity<Object> handleException(RessourceException ex, WebRequest webRequest) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Exception exception = new Exception();
        exception.setDateTime(LocalDateTime.now());
        exception.setStatus(status.value());
        exception.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, exception, new HttpHeaders(), status, webRequest);

    }
    
}
