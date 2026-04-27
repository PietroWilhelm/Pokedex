package fiap.com.br.Pokedex.controller;

import fiap.com.br.Pokedex.dto.TreinadorRequest;
import fiap.com.br.Pokedex.dto.TreinadorResponse;
import fiap.com.br.Pokedex.service.TreinadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/treinadores")
@RequiredArgsConstructor
public class TreinadorController {

    private final TreinadorService service;

    @PostMapping
    public ResponseEntity<TreinadorResponse> create(@RequestBody @Valid TreinadorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<TreinadorResponse>> findAll(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinadorResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<TreinadorResponse>> search(
            @RequestParam String nome,
            @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(service.findByNome(nome, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreinadorResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid TreinadorRequest request) {

        TreinadorResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }
}