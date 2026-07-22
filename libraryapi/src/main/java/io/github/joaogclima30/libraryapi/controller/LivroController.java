package io.github.joaogclima30.libraryapi.controller;

import io.github.joaogclima30.libraryapi.controller.dtoAutor.ErroDTO.ErroResponse;
import io.github.joaogclima30.libraryapi.controller.dtoAutor.autorDTO.AutorResponseDTO;
import io.github.joaogclima30.libraryapi.controller.dtoLivro.LivroRequestDTO;
import io.github.joaogclima30.libraryapi.controller.dtoLivro.LivroResponseDTO;
import io.github.joaogclima30.libraryapi.controller.mappers.LivroMapper;
import io.github.joaogclima30.libraryapi.exceptions.RegistroDuplicadoExceptions;
import io.github.joaogclima30.libraryapi.model.Autor;
import io.github.joaogclima30.libraryapi.model.Livro;
import io.github.joaogclima30.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    @PostMapping
    public ResponseEntity<Object> cadastroLivro(@RequestBody @Valid LivroRequestDTO livroDto) {
        Livro livro = livroMapper.toEntity(livroDto);
        livroService.salvar(livro);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livro.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<LivroResponseDTO> visualizarLivro(@PathVariable("id") String id) {
        return livroService.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    var dto = livroMapper.toDTO(livro);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteLivro(@Valid @PathVariable("id") String id){
        var idLivro = UUID.fromString(id);
        Optional<Livro> livroOptional = livroService.obterPorId(idLivro);
        livroService.deleteLivro(livroOptional.get());
        if(livroOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}