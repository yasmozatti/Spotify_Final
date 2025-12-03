package com.example.spotifytunado1.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Playlist> playlists;

    public Usuario() {}

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome(){
      return nome;
    }

    public Long getId(){
      return id;
    }

    public void setNome(String nome){
      this.nome = nome;
    }
  
}
