package com.example.spotifytunado1.repositories;

import com.example.spotifytunado1.entities.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepository extends JpaRepository<Musica, Long> { }
