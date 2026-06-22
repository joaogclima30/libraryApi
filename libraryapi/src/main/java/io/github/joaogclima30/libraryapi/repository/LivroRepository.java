package io.github.joaogclima30.libraryapi.repository;


import io.github.joaogclima30.libraryapi.model.Autor;
import io.github.joaogclima30.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //Query method
    //Mesma coisa que (select * from livro where id_autor = id)
    //List<Livro> findbyAutor(Autor autor);

    //Sempre colocar findby para pesquisa
    //select * from livro where titulo = 'Como programar'
    List<Livro> findByTitulo(String titulo);

    List<Livro> findByIsbn(String isbn);



}
