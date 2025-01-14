package br.com.zupacademy.jpcsik.proposta.errohandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErroHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroDto> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Collection<String> mensagens = new ArrayList<>();
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            String message = String.format("Campo: %s %s", fieldError.getField(), ", "+ fieldError.getDefaultMessage());
            mensagens.add(message);
        });

        ErroDto erroDto = new ErroDto(mensagens);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDto);
    }
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErroDto> handleResponseStatusException(ResponseStatusException exception) {
        Collection<String> mensagens = new ArrayList<>();
        mensagens.add(exception.getReason());

        ErroDto erroPadronizado = new ErroDto(mensagens);
        return ResponseEntity.status(exception.getStatus()).body(erroPadronizado);
    }

}
