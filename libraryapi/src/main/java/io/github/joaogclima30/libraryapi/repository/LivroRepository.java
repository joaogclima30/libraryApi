package io.github.joaogclima30.libraryapi.repository;


import io.github.joaogclima30.libraryapi.model.Autor;
import io.github.joaogclima30.libraryapi.model.GeneroLivro;
import io.github.joaogclima30.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/*
@see LivroRepositoryTest
 */
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

    //Vamos usar @Query, então pode colocar o nome que quiser
    @Query(" select l from Livro as l order by l.titulo ")
    List<Livro> listarTodos();

    @Query(" select a from Livro l join l.autor a ")
    List<Autor> listarAutoresDosLivros();

    //Query com paramentro
    @Query(" select l from Livro l where l.genero = :genero order by :paramOrdenação")
    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro, @Param("paramOrdenação") String nomePropriedade);

    //Metodo que vai deletar todos os livros de um determinado genero
    @Modifying
    @Transactional
    @Query(" delete from Livro where genero = ?1")
    void deleteByGenero(GeneroLivro generoLivro);

    @Modifying //Avisa o banco de dados que essa Query altera o banco
    @Transactional /*Ela informa que a operação deve ocorrer dentro de uma transação,
    Uma transação é um conjunto de operações que deve ser concluído corretamente ou desfeito em caso de erro.*/
    @Query(" update Livro set dataPublicacao = ?1")
    void updateDataPublicacao(LocalDate novaData);

    boolean existsByAutor(Autor autor);
}
