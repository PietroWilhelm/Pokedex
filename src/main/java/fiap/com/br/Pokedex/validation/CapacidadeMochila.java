package fiap.com.br.Pokedex.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD }) // Indica que a anotação vai em campos
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CapacidadeMochilaValidator.class)
public @interface CapacidadeMochila {
    String message() default "O treinador já possui o limite máximo de 6 pokébolas!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}