package fiap.com.br.Pokedex.controller;

import fiap.com.br.Pokedex.dto.TreinadorResponse;
import fiap.com.br.Pokedex.entity.Treinador;
import fiap.com.br.Pokedex.service.TreinadorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/treinadores")
@Slf4j
public class TreinadorController {

    @Autowired
    private TreinadorService service;

    @GetMapping
    public Page<Treinador> listAll(@PageableDefault(size = 10) Pageable pageable) {
        return service.getAllTreinadores(pageable);
    }

    @PostMapping
    public ResponseEntity<Treinador> createTreinador(@RequestBody Treinador treinador) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addTreinador(treinador));
    }

    @GetMapping("{id}")
    public ResponseEntity<Treinador> getById(@PathVariable Long id) {
        log.info("Obtendo dados do treinador {}", id);
        return ResponseEntity.ok(service.getTreinadorById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deletando treinador com id {}", id);
        service.deleteTreinador(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Treinador> update(@PathVariable Long id, @RequestBody Treinador treinador) {
        log.info("Atualizando treinador com id {} com os dados {}", id, treinador);
        return ResponseEntity.ok(service.updateTreinador(id, treinador));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<TreinadorResponse>> search(
            @RequestParam String nome,
            @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(service.findByNome(nome, pageable));
    }
}