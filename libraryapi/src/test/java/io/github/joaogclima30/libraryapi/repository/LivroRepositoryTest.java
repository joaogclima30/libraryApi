package io.github.joaogclima30.libraryapi.repository;


import io.github.joaogclima30.libraryapi.model.Autor;
import io.github.joaogclima30.libraryapi.model.GeneroLivro;
import io.github.joaogclima30.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@SpringBootTest
public class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void salvarLivroTest() {
        var livro = new Livro();

        livro.setPreco(BigDecimal.valueOf(89.99));
        livro.setTitulo("Banana");
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setDataPublicacao(LocalDate.of(2026, 02, 17));
        livro.setIsbn("AI QUE MEDINHO");

        //Forma de salvar sem ser cascada
        //Autor autor = autorRepository.findById(UUID.fromString("5f4855c4-80c5-431d-8b01-735236c26b3b")).orElse(null);

        //Cria o livro junto com o autor, um dependendo do outro, se apagar um livro, apaga o autor tbm
        /* Autor autor = new Autor ();
        autor.setNome("Joao");
        autor.setNacionalidade("Brasileira");
        autor.setData_nascimento(LocalDate.of(2007,8,26));
        */

       // Autor autor = autorRepository.findById(UUID.fromString("d71964a8-d976-4b86-971f-e98a3a2ca53a")).orElse(null);
        //livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    public void atualizarAutordoLivroTest() {
        UUID id = UUID.fromString("dfd30d0a-2ab0-42fa-beaf-75f5e7cbc502");
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        autorRepository.findById(UUID.fromString("dfd30d0a-2ab0-42fa-beaf-75f5e7cbc502")).orElse(null);

        livroRepository.save(livroParaAtualizar);
    }

    @Test
    public void countLivroTest() {
        System.out.println("Contagens de livros: " + livroRepository.count());

    }

    @Test
    public void deletarLivroTest() {
        var id = UUID.fromString("cc0f7f50-b74d-46dc-a799-b79eda1330f8");

        livroRepository.deleteById(id);
    }

    @Test
    @Transactional
    public void buscarLivroTest() {
        UUID id = UUID.fromString("dfd30d0a-2ab0-42fa-beaf-75f5e7cbc502");
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println("Livro");
        System.out.println(livro.getTitulo());

        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    public void pesquisaPorTituloTest() {
        List<Livro> lista = livroRepository.findByTitulo("Como programar");
        lista.forEach(System.out::println);
    }

    @Test
    public void pesquisaPorIsbnTestTest() {
        Optional<Livro> livro = livroRepository.findByIsbn("Programando em 2026");
        livro.ifPresent(System.out::println);
    }

    @Test
    public void listarLivrosComQueryTest(){
        var resultado = livroRepository.listarTodos();
        resultado.forEach(System.out::println);
     }

     @Test
    public void listarAutoresComQueryTest(){
        var resultado = livroRepository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
     }

     @Test
    public void listarPorGeneroQueryParamTest(){
        var resultado = livroRepository.findByGenero(GeneroLivro.ROMANCE, "dataPublicação");
        resultado.forEach(System.out::println);
     }

     @Test
    public void deletePorGeneroTest(){
        livroRepository.deleteByGenero(GeneroLivro.FICCAO);
     }

    @Test
    public void updateDataPublicacaoTest(){
        //livroRepository.updateDataPublicacao(LocalDate.of());
    }
}
