package fiap.com.br.Pokedex.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD}) // A anotação pode ser aplicada a campos (neste caso, ao campo "capacidadeMochila" do Treinador)
@Retention(RetentionPolicy.RUNTIME) // A anotação estará disponível em tempo de execução para que o validador possa acessá-la
@Constraint(validatedBy = CapacidadeMochilaValidator.class) // Especifica que a classe "CapacidadeMochilaValidator" é responsável por validar os campos anotados com @NivelPokemon
public @interface CapacidadeMochila {
    String message() default "A capacidade da mochila deve ser entre 1 e 6";

    Class<?>[] groups() default {};

    Class<? extends jakarta.validation.Payload>[] payload() default {};
}
