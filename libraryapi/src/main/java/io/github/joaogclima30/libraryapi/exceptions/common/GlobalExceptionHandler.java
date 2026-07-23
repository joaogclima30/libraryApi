package io.github.joaogclima30.libraryapi.exceptions.common;

import io.github.joaogclima30.libraryapi.DTOs.dtoAutor.ErroDTO.ErroCampo;
import io.github.joaogclima30.libraryapi.DTOs.dtoAutor.ErroDTO.ErroResponse;
import io.github.joaogclima30.libraryapi.exceptions.CampoInvalidoException;
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
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResponse handleRegistroDuplicadoException(RegistroDuplicadoExceptions e ){
        return new ErroResponse(HttpStatus.CONFLICT.value(),
                e.getMessage(),
                List.of());
    }

    @ExceptionHandler(ExisteLivroParaAutor.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResponse handleExisteLivroParaAutor(ExisteLivroParaAutor e){
        return new ErroResponse(HttpStatus.BAD_REQUEST.value(),
                "N é possivel deletar autor com livro",
                List.of());
    }
    @ExceptionHandler(CampoInvalidoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResponse handleCampoInvalidoException(CampoInvalidoException e){
        return new ErroResponse(HttpStatus.UNPROCESSABLE_ENTITY.value()
                , e.getMessage()
                ,List.of(new ErroCampo(e.getCampo(), e.getMessage())));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResponse handleErrosNaoTratados(RuntimeException e){
        return new ErroResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),"Ocorreu um erro inesperado, favor entrar em contato",
                List.of()
        );
    }

}
