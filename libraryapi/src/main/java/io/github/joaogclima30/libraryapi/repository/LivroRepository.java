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
    /*Um método query (ou consulta) é uma instrução enviada a um banco de dados ou sistema de informações para buscar,
    filtrar, alterar ou manipular dados. Ele traduz a sua necessidade em comandos lógicos que a máquina compreende e executa.
     */
    //Mesma coisa que (select * from livro where id_autor = id)
    //List<Livro> findbyAutor(Autor autor);

    //Sempre colocar findby para pesquisa
    //select * from livro where titulo = 'Como programar'
    List<Livro> findByTitulo(String titulo);

    List<Livro> findByIsbn(String isbn);



}
