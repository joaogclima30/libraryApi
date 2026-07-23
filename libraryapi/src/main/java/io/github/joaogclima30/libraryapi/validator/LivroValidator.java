package io.github.joaogclima30.libraryapi.validator;

import io.github.joaogclima30.libraryapi.exceptions.CampoInvalidoException;
import io.github.joaogclima30.libraryapi.exceptions.RegistroDuplicadoExceptions;
import io.github.joaogclima30.libraryapi.model.Livro;
import io.github.joaogclima30.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LivroValidator {

    private final LivroRepository livroRepository;

    public void validar(Livro livro){
        if (existeLivroComIsbn(livro)){
            throw new RegistroDuplicadoExceptions("ISBN ja cadastrado!");
        }

        if (isPrecoObrigatorioNulo(livro)){
            throw new CampoInvalidoException("Preco", "Para livros com ano de publicaçao apartir de 2020, o preco é obrigatorio");
        }
    }

    private boolean isPrecoObrigatorioNulo(Livro livro) {
        return livro.getPreco() == null &&
                livro.getDataPublicacao().getYear() >= 2020;
    }

    private boolean existeLivroComIsbn(Livro livro){
        Optional<Livro> livroEncontrado = livroRepository.findByIsbn(livro.getIsbn());

        if (livro.getId() == null){
            return livroEncontrado.isPresent();
        }

        return livroEncontrado
                .map(Livro::getId)
                .stream()
                .anyMatch(id -> !id.equals(livro.getId()));
    }
}
