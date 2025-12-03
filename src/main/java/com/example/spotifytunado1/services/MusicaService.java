package com.example.spotifytunado1.services;

import com.example.spotifytunado1.entities.Musica;
import com.example.spotifytunado1.repositories.MusicaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MusicaService {

    private final MusicaRepository repository;

    public MusicaService(MusicaRepository repository) {
        this.repository = repository;
    }

    public List<Musica> findAll() {
        return repository.findAll();
    }

    public Optional<Musica> findById(Long id) {
        return repository.findById(id);
    }

    public Musica save(Musica musica) {
        return repository.save(musica);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Musica> findByArtista(String artista) {
        return repository.findByArtista(artista);
    }

    public List<Musica> findByNome(String nome) {
        return repository.findByNome(nome);
    }
}
