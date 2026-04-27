package fiap.com.br.Pokedex.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE_USE, ElementType.FIELD }) // Indica que a anotação vai em campos
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NivelParaCapturaValidator.class)
public @interface NivelParaCaptura {
    String message() default "A pokébola escolhida não é adequada para o nível do Pokémon!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
