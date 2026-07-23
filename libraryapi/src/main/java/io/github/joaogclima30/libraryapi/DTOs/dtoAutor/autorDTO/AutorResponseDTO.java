package io.github.joaogclima30.libraryapi.DTOs.dtoAutor.autorDTO;

import java.time.LocalDate;
import java.util.UUID;

public record AutorResponseDTO(UUID id, String nome, LocalDate dataNascimento, String Nacionalidade) {

}
