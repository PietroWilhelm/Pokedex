package fiap.com.br.Pokedex.repository;

import fiap.com.br.Pokedex.entity.Treinador;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TreinadorRepository extends JpaRepository<Treinador, Long> {
}
