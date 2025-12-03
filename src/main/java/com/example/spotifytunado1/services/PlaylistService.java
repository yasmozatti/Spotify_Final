package com.example.spotifytunado1.services;

import com.example.spotifytunado1.entities.Musica;
import com.example.spotifytunado1.entities.Playlist;
import com.example.spotifytunado1.entities.Usuario;
import com.example.spotifytunado1.repositories.MusicaRepository;
import com.example.spotifytunado1.repositories.PlaylistRepository;
import com.example.spotifytunado1.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final MusicaRepository musicaRepository;
    private final UsuarioRepository usuarioRepository;

    public PlaylistService(PlaylistRepository playlistRepository, 
                          MusicaRepository musicaRepository,
                          UsuarioRepository usuarioRepository) {
        this.playlistRepository = playlistRepository;
        this.musicaRepository = musicaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    public Optional<Playlist> findById(Long id) {
        return playlistRepository.findById(id);
    }

    public Playlist save(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public Playlist criarPlaylist(String titulo, Long usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            Playlist playlist = new Playlist(titulo);
            playlist.setUsuario(usuario.get());
            return playlistRepository.save(playlist);
        }
        throw new RuntimeException("Usuário não encontrado");
    }

    public void adicionarMusica(Long playlistId, Long musicaId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        Optional<Musica> musica = musicaRepository.findById(musicaId);
        
        if (playlist.isPresent() && musica.isPresent()) {
            Playlist p = playlist.get();
            if (!p.getMusicas().contains(musica.get())) {
                p.getMusicas().add(musica.get());
                playlistRepository.save(p);
            }
        } else {
            throw new RuntimeException("Playlist ou Música não encontrada");
        }
    }

    public void removerMusica(Long playlistId, Long musicaId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        Optional<Musica> musica = musicaRepository.findById(musicaId);
        
        if (playlist.isPresent() && musica.isPresent()) {
            Playlist p = playlist.get();
            p.getMusicas().remove(musica.get());
            playlistRepository.save(p);
        } else {
            throw new RuntimeException("Playlist ou Música não encontrada");
        }
    }

    public void delete(Long id) {
        playlistRepository.deleteById(id);
    }

    public List<Musica> obterMusicas(Long playlistId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        if (playlist.isPresent()) {
            return playlist.get().getMusicas();
        }
        throw new RuntimeException("Playlist não encontrada");
    }
}
