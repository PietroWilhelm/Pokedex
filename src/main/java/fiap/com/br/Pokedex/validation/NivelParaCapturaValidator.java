package fiap.com.br.Pokedex.validation;

import fiap.com.br.Pokedex.dto.PokebolaRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class NivelParaCapturaValidator implements ConstraintValidator<NivelParaCaptura, PokebolaRequest> {

    @Override
    public boolean isValid(PokebolaRequest request, ConstraintValidatorContext context) {
        if (request == null || request.tipo() == null || request.nivelPokemon() == null) {
            return true;
        }

        String tipo = request.tipo().toUpperCase().trim();
        int nivel = request.nivelPokemon();

        // Lógica corrigida e segura:
        if (tipo.equals("COMUM") && nivel > 20) {
            return false;
        }

        if (tipo.equals("GREAT") && nivel > 50) {
            return false;
        }

        if (tipo.equals("ULTRA") && nivel > 80) {
            return false;
        }

        // MASTER sempre retorna true
        return true;
    }
}