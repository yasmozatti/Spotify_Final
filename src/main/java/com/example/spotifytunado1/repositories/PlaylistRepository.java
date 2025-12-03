package com.example.spotifytunado1.repositories;

import com.example.spotifytunado1.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> { }
