package fiap.com.br.Pokedex.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD}) // A anotação pode ser aplicada a campos (neste caso, ao campo "level" do Pokémon)
@Retention(RetentionPolicy.RUNTIME) // A anotação estará disponível em tempo de execução para que o validador possa acessá-la
@Constraint(validatedBy = NivelPokemonValidator.class) // Especifica que a classe "NivelPokemonValidator" é responsável por validar os campos anotados com @NivelPokemon
public @interface NivelPokemon { //  Definição da anotação personalizada de validação para o nível do Pokémon
    String message() default "O nível do Pokémon deve estar entre 1 e 100";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
