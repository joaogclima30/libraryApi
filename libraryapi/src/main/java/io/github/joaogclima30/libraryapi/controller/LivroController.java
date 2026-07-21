package io.github.joaogclima30.libraryapi.controller;

import io.github.joaogclima30.libraryapi.controller.dtoAutor.ErroDTO.ErroResponse;
import io.github.joaogclima30.libraryapi.controller.dtoLivro.LivroRequestDTO;
import io.github.joaogclima30.libraryapi.controller.mappers.LivroMapper;
import io.github.joaogclima30.libraryapi.exceptions.RegistroDuplicadoExceptions;
import io.github.joaogclima30.libraryapi.model.Livro;
import io.github.joaogclima30.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    @PostMapping
    public ResponseEntity<Object> cadastroLivro(@RequestBody @Valid LivroRequestDTO livroDto){
        try{
            Livro livro = livroMapper.toEntity(livroDto);
            livroService.salvar(livro);
            return ResponseEntity.ok(livro);
        }catch (RegistroDuplicadoExceptions e){
            var erroDTO = ErroResponse.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}
