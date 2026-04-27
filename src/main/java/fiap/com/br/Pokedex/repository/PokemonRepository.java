package fiap.com.br.Pokedex.repository;

import fiap.com.br.Pokedex.entity.Pokemon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    // Filtro 1: Buscar por tipo do Pokémon
    Page<Pokemon> findByTipoIgnoreCase(String tipo, Pageable pageable);

    // Filtro 2: Buscar por nome (contendo parte do nome)
    Page<Pokemon> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
