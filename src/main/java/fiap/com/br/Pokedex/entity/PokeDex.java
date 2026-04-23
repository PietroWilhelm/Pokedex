package fiap.com.br.Pokedex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "pokedexs")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PokeDex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String pokeBola;
    private Treinador treinador;
    private Pokemon pokemon;
}
