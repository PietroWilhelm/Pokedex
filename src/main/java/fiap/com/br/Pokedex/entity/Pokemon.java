package fiap.com.br.Pokedex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "pokemons")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tipo;
    private int level;
    private String ataque;
    
    @ManyToOne // Muitos Pokemons podem pertencer a um Treinador
    @JsonIgnore
    private Treinador treinador;

    @OneToOne // Um Pokemon pode estar contido em apenas uma Pokebola, e uma Pokebola pode conter apenas um Pokemon
    @JsonIgnore
    private Pokebola pokebola;
}
