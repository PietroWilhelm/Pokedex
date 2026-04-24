package fiap.com.br.Pokedex.validation;



import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CapacidadeMochilaValidator extends ConstraintValidator<CapacidadeMochila, Long> {

    @Autowired
    private PokebolaRepository repository;

    @Override
    public boolean isValid(Long treinadorId, ConstraintValidatorContext context) {
        if (treinadorId == null) return true; // Deixa para o @NotNull tratar

        // Conta quantas pokebolas este treinador possui no banco
        long totalPokebolas = repository.countByTreinadorId(treinadorId);

        return totalPokebolas < 6; // Retorna true se ainda houver espaço
    }
}
