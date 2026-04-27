package fiap.com.br.Pokedex.repository;

import fiap.com.br.Pokedex.entity.Pokebola;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PokebolaRepository extends JpaRepository<Pokebola, Long> {

    long countByTreinadorId(Long treinadorId);

    // Filtro 3: Buscar pokébolas de um treinador específico
    Page<Pokebola> findByTreinadorId(Long treinadorId, Pageable pageable);

    // Filtro 4: Buscar por tipo de pokébola (ex: só as "ULTRA")
    Page<Pokebola> findByTipoIgnoreCase(String tipo, Pageable pageable);
}
