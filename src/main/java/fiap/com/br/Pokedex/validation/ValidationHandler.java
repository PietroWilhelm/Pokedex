package fiap.com.br.Pokedex.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RestControllerAdvice
public class ValidationHandler {

    public record validationErrorResponse(String field, String message) {
        public validationErrorResponse(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<validationErrorResponse> handler(MethodArgumentNotValidException exception){

        // 1. Mapeia os erros de campos (ex: @NotBlank, @NotNull)
        List<validationErrorResponse> fieldErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(validationErrorResponse::new)
                .toList();

        // 2. Mapeia os erros globais (ex: @NivelParaCaptura que está no topo do Record)
        List<validationErrorResponse> globalErrors = exception.getBindingResult()
                .getGlobalErrors()
                .stream()
                .map(error -> new validationErrorResponse(error.getObjectName(), error.getDefaultMessage()))
                .toList();

        // 3. Une as duas listas para retornar tudo ao Postman
        return Stream.concat(fieldErrors.stream(), globalErrors.stream()).toList();
    }
}