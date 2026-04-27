package fiap.com.br.Pokedex.controller;

import fiap.com.br.Pokedex.dto.PokemonRequest;
import fiap.com.br.Pokedex.dto.PokemonResponse;
import fiap.com.br.Pokedex.service.PokemonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemons")
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService service;

    @PostMapping
    public ResponseEntity<PokemonResponse> create(@RequestBody @Valid PokemonRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<PokemonResponse>> findAll(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }


    @PutMapping("/{id}")
    public ResponseEntity<PokemonResponse> update(@PathVariable Long id, @RequestBody @Valid PokemonRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
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
        return ResponseEntity.ok(service.findAll(pageable));
    }
}