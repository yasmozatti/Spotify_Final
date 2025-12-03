package com.example.spotifytunado1.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
        name = "playlist_musicas",
        joinColumns = @JoinColumn(name = "playlist_id"),
        inverseJoinColumns = @JoinColumn(name = "musica_id")
    )
    private List<Musica> musicas;

    public Playlist() {}

    public Playlist(String titulo) {
        this.titulo = titulo;
    }

    public Long getId(){
      return id;
    }

    public String getTitulo(){
      return titulo;
    }

    public void setTitulo(String titulo){
      this.titulo = titulo;
    }
}
