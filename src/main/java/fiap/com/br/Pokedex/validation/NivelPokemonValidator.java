package fiap.com.br.Pokedex.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NivelPokemonValidator implements ConstraintValidator<NivelPokemon, Integer> {

    @Override
    public boolean isValid(Integer level, ConstraintValidatorContext context) {
        if (level == null) {
            return false; // validação simples que poderia ser feita com @NotNull, @Max e @Min, mas quis deixar a validação personalizada para treinar e aprender a criar uma anotação personalizada de validação
        }
        return level >= 1 && level <= 100;
    }
}
