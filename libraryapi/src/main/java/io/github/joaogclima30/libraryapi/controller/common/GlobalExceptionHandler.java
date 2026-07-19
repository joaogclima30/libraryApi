package io.github.joaogclima30.libraryapi.controller.common;

import io.github.joaogclima30.libraryapi.controller.dtoAutor.ErroDTO.ErroCampo;
import io.github.joaogclima30.libraryapi.controller.dtoAutor.ErroDTO.ErroResponse;
import io.github.joaogclima30.libraryapi.exceptions.ExisteLivroParaAutor;
import io.github.joaogclima30.libraryapi.exceptions.RegistroDuplicadoExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Nome pode ser da coisa que quer tratar o erro
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampo> listaErros = fieldErrors.stream().
                map(fieldError -> new ErroCampo(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErroResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro validação", listaErros);
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
