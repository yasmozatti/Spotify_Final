# Spotify Final — Backend com Spring Boot

Este projeto implementa um sistema de gerenciamento baseado em três entidades principais: Usuário, Playlist e Música. A aplicação foi desenvolvida com Spring Boot, utilizando Spring Web, Spring Data JPA e MySQL como banco de dados. As entidades possuem relacionamentos do tipo Um-para-Muitos e Muitos-para-Muitos, e a aplicação fornece operações completas de CRUD por meio de endpoints REST.

## Estrutura do Projeto

src/main/java/com/example/spotifytunado1
 ├── controllers
 │    ├── UsuarioController.java
 │    ├── PlaylistController.java
 │    └── MusicaController.java
 │
 ├── entities
 │    ├── Usuario.java
 │    ├── Playlist.java
 │    └── Musica.java
 │
 ├── repositories
 │    ├── UsuarioRepository.java
 │    ├── PlaylistRepository.java
 │    └── MusicaRepository.java
 │
 ├── services
 │    ├── UsuarioService.java
 │    ├── PlaylistService.java
 │    └── MusicaService.java
 │
 └── SpotifyTunado1Application.java

Descrição da Estrutura

Entities
Contém as classes que representam as tabelas do banco de dados.
Também definem os relacionamentos:

Usuário 1 → N Playlist

Playlist N ↔ N Música

Repositories
Interfaces que estendem JpaRepository, responsáveis por comunicar-se diretamente com o banco MySQL.

Services
Camada intermediária entre controllers e repositories.
Realiza as operações de CRUD chamando os repositórios.

Controllers
Expõem os endpoints REST da aplicação.
Permitem realizar operações de listar, criar e deletar registros.

Banco de Dados

A conexão com o MySQL é configurada pelo arquivo application.properties:

spring.application.name=SpotifyTunado1
spring.datasource.url=jdbc:mysql://localhost:3306/Spotify
spring.datasource.username=root
spring.datasource.password=123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true


O Hibernate atualiza automaticamente as tabelas conforme as entidades são alteradas.

Como Executar o Projeto
1. Instalar ou rodar pelo navegador

O projeto pode ser executado localmente com Maven ou por ambientes como GitHub Codespaces ou GitPod.

2. Executar o servidor

No terminal da IDE ou do Codespaces:

mvn spring-boot:run


A aplicação ficará disponível em:

http://localhost:8080


Ou no caso de Codespaces/GitPod, será gerada uma URL pública equivalente.

Endpoints Disponíveis
Usuários

Listar todos

GET /usuarios


Criar usuário

POST /usuarios


Body JSON:

{
  "nome": "Maria"
}


Excluir usuário

DELETE /usuarios/{id}

Músicas

Listar todas

GET /musicas


Criar música

POST /musicas


Body:

{
  "nome": "Believer",
  "artista": "Imagine Dragons"
}


Excluir música

DELETE /musicas/{id}

Playlists

Listar todas

GET /playlists

Criar playlist

POST /playlists

Body:

{
  "titulo": "Treino",
  "usuario": { "id": 1 },
  "musicas": [
    { "id": 1 }
  ]
}

Excluir playlist

DELETE /playlists/{id}
5.  Acessar a API em: `http://localhost:8080/`
