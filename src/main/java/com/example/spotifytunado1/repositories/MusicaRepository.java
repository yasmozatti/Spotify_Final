package com.example.spotifytunado1.repositories;

import com.example.spotifytunado1.entities.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
    List<Musica> findByArtista(String artista);
    List<Musica> findByNome(String nome);
}
