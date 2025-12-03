package com.example.spotifytunado1.services;

import com.example.spotifytunado1.entities.Usuario;
import com.example.spotifytunado1.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
