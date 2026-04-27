package fiap.com.br.Pokedex.controller;

import fiap.com.br.Pokedex.dto.PokebolaRequest;
import fiap.com.br.Pokedex.dto.PokebolaResponse;
import fiap.com.br.Pokedex.service.PokebolaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokebolas")
@RequiredArgsConstructor
public class PokebolaController {

    private final PokebolaService service;

    @PostMapping
    public ResponseEntity<PokebolaResponse> create(@RequestBody @Valid PokebolaRequest request) {
        // Dispara as validações customizadas: @CapacidadeMochila e @NivelParaCaptura
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<PokebolaResponse>> findAll(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokebolaResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
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
        return ResponseEntity.ok(service.findAll(pageable));
    }

}