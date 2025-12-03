package com.example.spotifytunado1.controllers;

import com.example.spotifytunado1.entities.Musica;
import com.example.spotifytunado1.entities.Playlist;
import com.example.spotifytunado1.services.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService service;

    public PlaylistController(PlaylistService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> obter(@PathVariable Long id) {
        Optional<Playlist> playlist = service.findById(id);
        return playlist.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Playlist> criar(@RequestBody Playlist playlist) {
        return ResponseEntity.status(HttpStatus.CREATED)
                           .body(service.save(playlist));
    }

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<Playlist> criarPlaylist(
            @PathVariable Long usuarioId,
            @RequestParam String titulo) {
        try {
            Playlist playlist = service.criarPlaylist(titulo, usuarioId);
            return ResponseEntity.status(HttpStatus.CREATED).body(playlist);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Playlist> atualizar(
            @PathVariable Long id,
            @RequestBody Playlist playlistAtualizada) {
        Optional<Playlist> playlist = service.findById(id);
        if (playlist.isPresent()) {
            Playlist p = playlist.get();
            p.setTitulo(playlistAtualizada.getTitulo());
            return ResponseEntity.ok(service.save(p));
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

    @PostMapping("/{playlistId}/musicas/{musicaId}")
    public ResponseEntity<Void> adicionarMusica(
            @PathVariable Long playlistId,
            @PathVariable Long musicaId) {
        try {
            service.adicionarMusica(playlistId, musicaId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{playlistId}/musicas/{musicaId}")
    public ResponseEntity<Void> removerMusica(
            @PathVariable Long playlistId,
            @PathVariable Long musicaId) {
        try {
            service.removerMusica(playlistId, musicaId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{playlistId}/musicas")
    public ResponseEntity<List<Musica>> obterMusicas(@PathVariable Long playlistId) {
        try {
            return ResponseEntity.ok(service.obterMusicas(playlistId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
