package io.github.joaogclima30.libraryapi.controller.dtoLivro;

import io.github.joaogclima30.libraryapi.model.Autor;
import io.github.joaogclima30.libraryapi.model.GeneroLivro;
import io.github.joaogclima30.libraryapi.model.Livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LivroRequestDTO(@NotBlank(message = "Campo Obrigatorio") @ISBN String isbn,
                              @NotBlank(message = "Campo Obrigatorio") String titulo,
                              @NotNull(message = "Campo Obrigatorio") @Past(message = "N pode ser data Futura") LocalDate dataPublicacao,
                              @NotNull GeneroLivro generoLivro,
                              BigDecimal preco,
                              @NotNull(message = "Campo Obrigatorio") UUID id_autor){

}
