package io.github.joaogclima30.libraryapi.repository;

import io.github.joaogclima30.libraryapi.model.Autor;
import io.github.joaogclima30.libraryapi.model.GeneroLivro;
import io.github.joaogclima30.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Testes com Repositorio (PODE USAR COMO MOLDE)
@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Joao Gabriel ");
        autor.setNacionalidade("brasil");
        autor.setData_nascimento(LocalDate.of(2007,8,26));

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor salvo com sucesso!");
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("c00e18c1-8446-4f7f-ab7d-e7ddbe18c4b5");

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if (possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor");
            System.out.println(autorEncontrado);

            autorEncontrado.setNome("Maria Eduarda Nardelli");
            autorRepository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = autorRepository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contade de autores: " + autorRepository.count());

    }

    @Test
    public void deletePorIdTeste(){
        var id = UUID.fromString ("c198aff7-79b5-4011-b73f-495155f97442");
        autorRepository.deleteById(id);
    }

    @Test
    //Salva o autor e o livro juntos
    public void salvarAutorComLivroTest(){
        Autor autor = new Autor();
        autor.setNome("Madu");
        autor.setNacionalidade("Italiana");
        autor.setData_nascimento(LocalDate.of(2010, 07, 10));

        var livro = new Livro();
        livro.setPreco(BigDecimal.valueOf(99.99));
        livro.setTitulo("Amor a primeira vista");
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setDataPublicacao(LocalDate.of(2026,06,18));
        livro.setIsbn("3006-2022");

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);

        autorRepository.save(autor);

        livroRepository.saveAll(autor.getLivros());
    }

    //Pesquisa no banco de dados
}
