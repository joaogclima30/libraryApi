package io.github.joaogclima30.libraryapi.controller.dto.autorDTO;

import io.github.joaogclima30.libraryapi.model.Autor;

import java.time.LocalDate;
import java.util.UUID;

public record AutorResponseDTO(UUID id, String nome, LocalDate dataNascimento, String Nacionalidade) {

    public Autor respostaParaAutor(){
        var autor = new Autor();
        autor.getId();
        autor.getNome();
        autor.getDataNascimento();
        autor.getNacionalidade();
        return autor;
    }
}
