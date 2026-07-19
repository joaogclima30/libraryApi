package io.github.joaogclima30.libraryapi.service;

import io.github.joaogclima30.libraryapi.model.Autor;
import io.github.joaogclima30.libraryapi.model.GeneroLivro;
import io.github.joaogclima30.libraryapi.model.Livro;
import io.github.joaogclima30.libraryapi.repository.AutorRepository;
import io.github.joaogclima30.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository.findById
                (UUID.fromString("bb5d8cd3-ee6a-4a50-84c3-2faae272cf45")).orElse(null);
        livro .setDataPublicacao(LocalDate.of(2024,7,2));

        //N é preciso fazer um save no final pois o metodo é Transactional
        // livroRepository.save(livro);
    }
    @Transactional
    public void executar(){
        //Salva livro
        var livro = new Livro();
        livro.setPreco(BigDecimal.valueOf(89.99));
        livro.setTitulo("Vai dar erro");
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setDataPublicacao(LocalDate.of(2026, 07, 17));
        livro.setIsbn("AI QUE MEDINHO");

        //Salva autor
        Autor autor = new Autor ();
        autor.setNome("Joao");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2007,8,26));

        /*saveAndFlush (ele ja manda a operação para o banco na mesma hora,
         e o save normal ele manda no final da operação.*/
        livroRepository.save(livro);
        livro.setAutor(autor);

        if(livro.getTitulo().equals("Não Vai dar erro")) {
            throw new RuntimeException("RollBack");
        }
    }
}
