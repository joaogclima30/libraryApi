package io.github.joaogclima30.libraryapi.DTOs.dtoLivro;

import io.github.joaogclima30.libraryapi.model.GeneroLivro;

public record ResultadoPesquisaLivroDTO(String isbn, String titulo, String nomeAutor, GeneroLivro generoLivro, Integer anoPublicacao) {
}
