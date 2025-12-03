package com.example.spotifytunado1.repositories;

import com.example.spotifytunado1.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> { }
