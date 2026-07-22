package io.github.joaogclima30.libraryapi.controller.dtoLivro;

import io.github.joaogclima30.libraryapi.controller.dtoAutor.autorDTO.AutorResponseDTO;
import io.github.joaogclima30.libraryapi.model.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LivroResponseDTO(UUID id,
                               String isbn,
                               String titulo,
                               LocalDate dataPublicacao,
                               GeneroLivro generoLivro,
                               BigDecimal preco,
                               AutorResponseDTO autorDto) {
}
