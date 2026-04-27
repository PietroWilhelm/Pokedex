package fiap.com.br.Pokedex.repository;

import fiap.com.br.Pokedex.entity.Pokebola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PokebolaRepository extends JpaRepository<Pokebola, Long> {

    long countByTreinadorId(Long treinadorId);
}
