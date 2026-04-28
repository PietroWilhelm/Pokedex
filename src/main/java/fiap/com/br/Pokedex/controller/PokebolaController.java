package fiap.com.br.Pokedex.controller;

import fiap.com.br.Pokedex.dto.PokebolaRequest;
import fiap.com.br.Pokedex.dto.PokebolaResponse;
import fiap.com.br.Pokedex.entity.Pokebola;
import fiap.com.br.Pokedex.service.PokebolaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokebolas")
@Slf4j
public class PokebolaController {

    @Autowired
    private PokebolaService service;

    @GetMapping
    public Page<Pokebola> listAll(@PageableDefault(size = 5) Pageable pageable) {
        return service.getAllPokebolas(pageable);
    }

    @PostMapping

    public ResponseEntity<PokebolaResponse> create(@RequestBody @Valid PokebolaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createPokemon(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<Pokebola> getById(@PathVariable Long id) {
        log.info("Obtendo dados da pokébola {}", id);
        return ResponseEntity.ok(service.getPokebolaById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deletando pokébola com id {}", id);
        service.deletePokebola(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Pokebola> updatePokebola(@PathVariable Long id, @RequestBody Pokebola pokebola) {
        log.info("Atualizando pokébola id {}", id);
        return ResponseEntity.ok(service.updatePokebola(id, pokebola));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PokebolaResponse>> search(
            @RequestParam(required = false) Long treinadorId,
            @RequestParam(required = false) String tipo,
            @PageableDefault(size = 5) Pageable pageable) {

        if (treinadorId != null) {
            return ResponseEntity.ok(service.findByTreinador(treinadorId, pageable));
        }
        if (tipo != null) {
            return ResponseEntity.ok(service.findByTipo(tipo, pageable));
        }
        return ResponseEntity.ok(service.getAllPokebolas(pageable).map(PokebolaResponse::fromEntity));
    }
}