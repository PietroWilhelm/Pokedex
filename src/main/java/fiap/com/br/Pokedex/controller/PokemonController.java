package fiap.com.br.Pokedex.controller;

import fiap.com.br.Pokedex.dto.PokemonResponse;
import fiap.com.br.Pokedex.entity.Pokemon;
import fiap.com.br.Pokedex.service.PokemonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemons")
@Slf4j
public class PokemonController {

    @Autowired
    private PokemonService service;

    @GetMapping
    public Page<Pokemon> listAll(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return service.getAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addPokemon(pokemon));
    }

    @GetMapping("{id}")
    public ResponseEntity<Pokemon> getById(@PathVariable Long id) {
        log.info("Obtendo dados do pokémon {}", id);
        return ResponseEntity.ok(service.getPokemonById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deletando pokémon com id {}", id);
        service.deletePokemon(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Pokemon> update(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        log.info("Atualizando pokémon com id {} com os dados {}", id, pokemon);
        return ResponseEntity.ok(service.updatePokemon(id, pokemon));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PokemonResponse>> search(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String nome,
            @PageableDefault(size = 10) Pageable pageable) {

        if (tipo != null) {
            return ResponseEntity.ok(service.findByTipo(tipo, pageable));
        }
        if (nome != null) {
            return ResponseEntity.ok(service.findByNome(nome, pageable));
        }
        return ResponseEntity.ok(service.getAll(pageable).map(PokemonResponse::fromEntity));
    }
}