package io.github.joaogclima30.libraryapi.repository;

import io.github.joaogclima30.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;

    /*
    Commit -> Confirmar Alterações
    RollBack -> desfazer Alterações
     */
    @Test
    @Transactional
    void transacaoSimples(){
        //Salvar um livro
        //Salvar o autor
        //Alugar o livro
        //Enviar email pro locatário
        //Notificar que o livro saiu da livraria
        //Se tiver erro em algum desses acontece o rollback

        transacaoService.executar();
    }

    @Test
    void transacaoEstadoManaged(){
        transacaoService.atualizacaoSemAtualizar();
    }

}
