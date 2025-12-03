package com.example.spotifytunado1.controllers;

import com.example.spotifytunado1.entities.Musica;
import com.example.spotifytunado1.services.MusicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    private final MusicaService service;

    public MusicaController(MusicaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Musica>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musica> obter(@PathVariable Long id) {
        Optional<Musica> musica = service.findById(id);
        return musica.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Musica> criar(@RequestBody Musica musica) {
        return ResponseEntity.status(HttpStatus.CREATED)
                           .body(service.save(musica));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Musica> atualizar(
            @PathVariable Long id,
            @RequestBody Musica musicaAtualizada) {
        Optional<Musica> musica = service.findById(id);
        if (musica.isPresent()) {
            Musica m = musica.get();
            m.setNome(musicaAtualizada.getNome());
            m.setArtista(musicaAtualizada.getArtista());
            return ResponseEntity.ok(service.save(m));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Musica>> buscarPorArtista(@RequestParam String artista) {
        return ResponseEntity.ok(service.findByArtista(artista));
    }

    @GetMapping("/buscar-nome")
    public ResponseEntity<List<Musica>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(service.findByNome(nome));
    }
}
