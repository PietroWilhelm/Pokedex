package fiap.com.br.Pokedex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "treinadores")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Treinador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int capacidadeMochila;

    @OneToMany // Um Treinador pode ter muitos Pokemons
    @JsonIgnore
    private List<Pokemon> pokemons;

    @OneToMany // Um Treinador pode ter muitas Pokebolas
    @JsonIgnore
    private List<Pokebola> pokebolas;
}
