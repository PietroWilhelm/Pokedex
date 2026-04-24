package fiap.com.br.Pokedex.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ValidationHandler { // Classe de tratamento global de exceções para lidar com erros de validação

    // Classe interna para representar a resposta de erro de validação
    public record validationErrorResponse(String field, String message) {
        public validationErrorResponse(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

    // Método para lidar com exceções de validação e retornar uma lista de erros de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<validationErrorResponse> handler(MethodArgumentNotValidException exception){
        return exception.getFieldErrors().stream()
                .map(validationErrorResponse::new)
                .toList();
    }
}
