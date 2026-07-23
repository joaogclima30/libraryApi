package io.github.joaogclima30.libraryapi.repository.specs;

import io.github.joaogclima30.libraryapi.model.GeneroLivro;
import io.github.joaogclima30.libraryapi.model.Livro;
import org.springframework.data.jpa.domain.Specification;


public class LivroSpecs {

    public static Specification<Livro> isbnEqual(String isbn){
        return ((root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("isbn"), isbn));
    }

    public static Specification<Livro> tituloLike(String titulo){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%");
    }

    public static Specification<Livro> generoEqual(GeneroLivro generoLivro){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("genero"), generoLivro);
    }

    public static Specification<Livro> anoPublicacaoEquals(Integer anoPublicacao){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        criteriaBuilder
                                .function("to_char", String.class, root.get("dataPublicacao"), criteriaBuilder.literal("YYYY"))
                        , anoPublicacao.toString());
    }

    public static Specification<Livro> nomeAutorLike(String nome){
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.like( criteriaBuilder.upper(root.get("autor").get("nome")), "%" + nome.toUpperCase() + "%");
    }
}
