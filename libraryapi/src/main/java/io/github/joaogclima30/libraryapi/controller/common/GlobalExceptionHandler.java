package io.github.joaogclima30.libraryapi.controller.common;

import io.github.joaogclima30.libraryapi.controller.dto.ErroDTO.ErroResponse;
import io.github.joaogclima30.libraryapi.exceptions.DeleteAutorQueTemLivro;
import io.github.joaogclima30.libraryapi.exceptions.ExisteLivroParaAutor;
import io.github.joaogclima30.libraryapi.exceptions.RegistroDuplicadoExceptions;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Nome pode ser da coisa que quer tratar o erro
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        System.out.println(e);
        return ErroResponse.respostaPadrao("Erro Validação");
    }

    @ExceptionHandler(RegistroDuplicadoExceptions.class)
    public ErroResponse handleRegistroDuplicadoException(RegistroDuplicadoExceptions e ){
        System.out.println(e);
        return ErroResponse.conflito("N é possivel registrar Autor Pois ja existe");
    }

    @ExceptionHandler(ExisteLivroParaAutor.class)
    public ErroResponse handleExisteLivroParaAutor(ExisteLivroParaAutor e){
        System.out.println(e);
        return ErroResponse.respostaPadrao("N é possivel apagar autor que tem livro");
    }

}
