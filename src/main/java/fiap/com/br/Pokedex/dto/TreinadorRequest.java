package fiap.com.br.Pokedex.dto;

import jakarta.validation.constraints.NotBlank;

public record TreinadorRequest (

  @NotBlank(message = "O nome do Treinador é obrigatório")
  String nome,

  int capacidadeMochila
) {

}
