# Mini Spotify -- Projeto Spring Boot

Este projeto implementa uma aplicação web simples inspirada no Spotify,
utilizando Spring Boot, seguindo os requisitos de CRUD e relacionamentos
entre entidades.

## Objetivo

Criar um sistema básico de gerenciamento de músicas, playlists e
usuários, implementando quatro tipos de relacionamentos: - Um-para-Um -
Um-para-Muitos - Muitos-para-Um - Muitos-para-Muitos

O projeto inclui CRUD completo (Create, Read e Delete) para cada
entidade.

# Estrutura das Entidades

## 1. Song (Música)

Representa uma música cadastrada no sistema.

**Atributos:** - id - title - artist - genre - duration - playlists
(relacionamento muitos-para-muitos)

**Relacionamentos:** - Muitos-para-Muitos com Playlist

## 2. Playlist

Representa uma playlist criada por um usuário.

**Atributos:** - id - name - description - songs (músicas vinculadas) -
owner (usuário dono da playlist)

**Relacionamentos:** - Muitos-para-Muitos com Song - Muitos-para-Um com
User

## 3. User

Representa um usuário do sistema.

**Atributos:** - id - name - email - playlists (playlists que o usuário
criou) - favoritePlaylist (playlist favorita)

**Relacionamentos:** - Um-para-Muitos com Playlist - Um-para-Um com
Playlist (favoritePlaylist)

# Relacionamentos Implementados

## 1. Um-para-Um (User → FavoritePlaylist)

Cada usuário possui uma única playlist favorita.

    @OneToOne
    @JoinColumn(name = "favorite_playlist_id")
    private Playlist favoritePlaylist;

## 2. Um-para-Muitos (User → Playlists)

Um usuário pode criar várias playlists.

    @OneToMany(mappedBy = "owner")
    private List<Playlist> playlists;

## 3. Muitos-para-Um (Playlist → Owner)

Várias playlists podem ter o mesmo usuário como dono.

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

## 4. Muitos-para-Muitos (Playlist ↔ Songs)

Uma playlist pode conter várias músicas e cada música pode estar em
várias playlists.

    @ManyToMany
    @JoinTable(
        name = "playlist_song",
        joinColumns = @JoinColumn(name = "playlist_id"),
        inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> songs;

# Estrutura do Projeto

O projeto segue a arquitetura padrão de três camadas:

## 1. Models (Entidades)

Localizados em `model/`, representam as tabelas do sistema.

## 2. Repositories

Localizados em `repository/`, estendem `JpaRepository` e permitem
operações no banco.

Exemplos: - SongRepository - PlaylistRepository - UserRepository

## 3. Services

Localizados em `service/`, contêm a lógica de negócio.

Exemplos: - SongService - PlaylistService - UserService

## 4. Controllers

Localizados em `controller/`, expõem a API REST e tratam das
requisições.

Exemplos: - SongController - PlaylistController - UserController

# Funcionalidades CRUD

## Song

-   GET `/songs` -- Lista todas as músicas
-   POST `/songs` -- Adiciona nova música
-   DELETE `/songs/{id}` -- Remove música pelo ID

## Playlist

-   GET `/playlists` -- Lista todas as playlists
-   POST `/playlists` -- Adiciona nova playlist
-   DELETE `/playlists/{id}` -- Remove playlist pelo ID

## User

-   GET `/users` -- Lista todos os usuários
-   POST `/users` -- Adiciona novo usuário
-   DELETE `/users/{id}` -- Remove usuário pelo ID

# Como Rodar o Projeto

1.  Instalar Java 17 ou superior\
2.  Instalar Maven\
3.  Clonar o repositório\
4.  Rodar o comando:

```{=html}
<!-- -->
```
    mvn spring-boot:run

5.  Acessar a API em: `http://localhost:8080/`
