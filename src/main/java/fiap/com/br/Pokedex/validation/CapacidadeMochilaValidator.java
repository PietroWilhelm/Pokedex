package fiap.com.br.Pokedex.validation;



import fiap.com.br.Pokedex.repository.PokebolaRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class CapacidadeMochilaValidator implements ConstraintValidator<CapacidadeMochila, Long> {

    @Autowired
    private PokebolaRepository repository;

    @Override
    public boolean isValid(Long treinadorId, ConstraintValidatorContext context) {
        // Se o ID for nulo, a validação passa aqui (o @NotNull cuidará disso no DTO)
        if (treinadorId == null) {
            return true;
        }

        // Chamada ao método que definimos no Passo 1
        long total = repository.countByTreinadorId(treinadorId);

        // Se o total for menor que 6, retorna true (válido). Se for 6 ou mais, retorna false (inválido).
        return total < 6;
    }
}
