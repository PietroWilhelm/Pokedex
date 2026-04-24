package fiap.com.br.Pokedex.repository;

import fiap.com.br.Pokedex.entity.Pokebola;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PokedexRepository extends JpaRepository<Pokebola, Long> {
}
