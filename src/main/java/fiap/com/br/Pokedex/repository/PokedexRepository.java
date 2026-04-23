package fiap.com.br.Pokedex.repository;

import fiap.com.br.Pokedex.entity.PokeDex;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PokedexRepository extends JpaRepository<PokeDex, Long> {
}
