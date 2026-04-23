package fiap.com.br.Pokedex.repository;

import fiap.com.br.Pokedex.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
