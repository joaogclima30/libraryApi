package io.github.joaogclima30.libraryapi.controller.dto.autorDTO;

import io.github.joaogclima30.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

//@NotBlack para string, @NotNull para valores
public record AutorRequestDTO(@NotBlank(message = "Campo obrigatorio") String nome,
                              @NotNull(message = "Campo obrigatorio") LocalDate dataNascimento,
                              @NotBlank(message = "Campo obrigatorio") String nacionalidade) {

    public Autor mapearParaAutor(){
        var autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}