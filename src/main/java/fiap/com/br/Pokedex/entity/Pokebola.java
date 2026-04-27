package fiap.com.br.Pokedex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "pokebolas")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pokebola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tipo; // Ex: "COMUM", GREAT "ULTRA", "MASTER"

    @ManyToOne // Muitas Pokebolas podem pertencer a um Treinador, mas uma Pokebola só pode pertencer a um Treinador
    @JoinColumn(name = "treinador_id") // Especifica a coluna de junção para a relação ManyToOne
    private Treinador treinador;

    @OneToOne // Uma Pokebola pode conter um Pokemon, e um Pokemon pode estar contido em apenas uma Pokebola
    @JoinColumn(name = "pokemon_id") // Especifica a coluna de junção para a relação OneToOne
    private Pokemon pokemon;
}
