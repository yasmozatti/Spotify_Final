package com.example.spotifytunado1.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String artista;

    @ManyToMany(mappedBy = "musicas")
    private List<Playlist> playlists;

    public Musica() {}

    public Musica(String nome, String artista) {
        this.nome = nome;
        this.artista = artista;
    }

    public String getNome(){
      return nome;
    }

    public String getArtista(){
      return artista;
    }
}
