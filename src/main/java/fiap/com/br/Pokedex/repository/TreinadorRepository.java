package fiap.com.br.Pokedex.repository;

import fiap.com.br.Pokedex.entity.Treinador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TreinadorRepository extends JpaRepository<Treinador, Long> {

    // Filtro 5: Buscar treinador por nome
    Page<Treinador> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
