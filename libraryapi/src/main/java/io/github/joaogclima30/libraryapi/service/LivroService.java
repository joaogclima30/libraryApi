package io.github.joaogclima30.libraryapi.service;

import io.github.joaogclima30.libraryapi.Security.SecurityService;
import io.github.joaogclima30.libraryapi.model.GeneroLivro;
import io.github.joaogclima30.libraryapi.model.Livro;
import io.github.joaogclima30.libraryapi.model.Usuario;
import io.github.joaogclima30.libraryapi.repository.LivroRepository;
import io.github.joaogclima30.libraryapi.repository.specs.LivroSpecs;
import io.github.joaogclima30.libraryapi.validator.LivroValidator;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.joaogclima30.libraryapi.repository.specs.LivroSpecs.anoPublicacaoEquals;
import static io.github.joaogclima30.libraryapi.repository.specs.LivroSpecs.nomeAutorLike;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final SecurityService securityService;
    private final LivroValidator livroValidator;

    public Livro salvar(Livro livro){
        livroValidator.validar(livro);
        Usuario usuario = securityService.obterUsuarioLogado();
        livro.setUsuario(usuario);
        return livroRepository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id){
        return livroRepository.findById(id);
    }

    public void deleteLivro(Livro livro){
        livroRepository.delete(livro);
    }

    //Metodo de pesquisa mais avançado soq completamente superior USAR
    public List<Livro> pesquisa(String isbn, String titulo, String nomeAutor, GeneroLivro generoLivro, Integer anoPublicacao){

//        Specification<Livro> specs = Specification
//                .where(LivroSpecs.isbnEqual(isbn))
//                .and(LivroSpecs.tituloLike(titulo))
//                .and(LivroSpecs.generoEqual(generoLivro));

        Specification<Livro> specs = Specification
                .where(((root, query, criteriaBuilder) -> criteriaBuilder.conjunction()));
        // query = query and isbn = :isbn
        if(isbn != null){
            specs = specs.and(LivroSpecs.isbnEqual(isbn));
        }
        if(titulo != null){
            specs = specs.and(LivroSpecs.tituloLike(titulo));
        }
        if(generoLivro != null){
            specs = specs.and(LivroSpecs.generoEqual(generoLivro));
        }
        if(anoPublicacao != null){
            specs = specs.and(anoPublicacaoEquals(anoPublicacao));
        }
        if(nomeAutor != null){
            specs = specs.and(nomeAutorLike(nomeAutor));
        }

        return livroRepository.findAll(specs);
    }

    public void atualizar(Livro livro){
        if (livro.getId() == null){
            throw new IllegalArgumentException("Para atualizar, é necessario que o livro esteja salvo");
        }

        livroValidator.validar(livro);
        livroRepository.save(livro);
    }

}
