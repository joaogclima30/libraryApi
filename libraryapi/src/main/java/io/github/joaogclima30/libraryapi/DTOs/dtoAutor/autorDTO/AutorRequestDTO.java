package io.github.joaogclima30.libraryapi.DTOs.dtoAutor.autorDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

//@NotBlack para string, @NotNull para valores
public record AutorRequestDTO(@NotBlank(message = "Campo obrigatorio")@Size(min = 2, max = 100, message = "Campo fora do tamanho padrão")String nome,
                              @NotNull(message = "Campo obrigatorio")@Past(message = "N pode ser uma data futura") LocalDate dataNascimento,
                              @NotBlank(message = "Campo obrigatorio")@Size(min = 2, max = 50, message = "Campo fora do tamanho padrão")String nacionalidade) {

}