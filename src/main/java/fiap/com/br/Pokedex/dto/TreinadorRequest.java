package fiap.com.br.Pokedex.dto;

import fiap.com.br.Pokedex.entity.Treinador;

import jakarta.validation.constraints.NotBlank;

public record TreinadorRequest (

  @NotBlank(message = "O nome do Treinador é obrigatório")
  String nome,


  int capacidadeMochila

) {
    public Treinador toEntity(){
        return Treinador.builder()
                .nome(nome)
                .capacidadeMochila(capacidadeMochila)
                .build();
    }
}
