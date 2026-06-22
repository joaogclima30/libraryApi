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
import java.util.UUID;


//
@SpringBootTest
public class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void salvarLivro() {
        var livro = new Livro();

        livro.setPreco(BigDecimal.valueOf(150.20));
        livro.setTitulo("Como programar");
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setDataPublicacao(LocalDate.of(2026,07,17));
        livro.setIsbn("Programando em 2026");

        //Forma de salvar sem ser cascada
        Autor autor = autorRepository.findById(UUID.fromString("c00e18c1-8446-4f7f-ab7d-e7ddbe18c4b5")).orElse(null);

        //Cria o livro junto com o autor, um dependendo do outro, se apagar um livro, apaga o autor tbm
        /* Autor autor = new Autor ();
        autor.setNome("Joao");
        autor.setNacionalidade("Brasileira");
        autor.setData_nascimento(LocalDate.of(2007,8,26));
        */

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    public void atualizarAutordoLivro(){
        UUID id = UUID.fromString("dfd30d0a-2ab0-42fa-beaf-75f5e7cbc502");
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        autorRepository.findById(UUID.fromString("dfd30d0a-2ab0-42fa-beaf-75f5e7cbc502")).orElse(null);

        livroRepository.save(livroParaAtualizar);
    }

    @Test
    public void countLivro(){
        System.out.println("Contagens de livros: " + livroRepository.count());

    }

    @Test
    public void deletarLivro(){
        var id = UUID.fromString("cc0f7f50-b74d-46dc-a799-b79eda1330f8");

        livroRepository.deleteById(id);
    }

    @Test
    @Transactional
    public void buscarLivroTeste(){
        UUID id = UUID.fromString("dfd30d0a-2ab0-42fa-beaf-75f5e7cbc502");
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println("Livro");
        System.out.println(livro.getTitulo());

        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    public void pesquisaPorTituloTest(){
        List<Livro> lista = livroRepository.findByTitulo("Como programar");
        lista.forEach(System.out::println);
    }

    @Test
    public void pesquisaPorIsbnTest(){
        List<Livro> lista = livroRepository.findByIsbn("Programando em 2026");
        lista.forEach(System.out::println);
    }
}
