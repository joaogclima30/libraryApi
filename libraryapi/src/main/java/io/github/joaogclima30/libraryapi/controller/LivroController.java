package io.github.joaogclima30.libraryapi.controller;

import io.github.joaogclima30.libraryapi.DTOs.dtoLivro.LivroRequestDTO;
import io.github.joaogclima30.libraryapi.DTOs.dtoLivro.LivroResponseDTO;
import io.github.joaogclima30.libraryapi.mappers.LivroMapper;
import io.github.joaogclima30.libraryapi.model.GeneroLivro;
import io.github.joaogclima30.libraryapi.model.Livro;
import io.github.joaogclima30.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
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
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    public ResponseEntity<LivroResponseDTO> visualizarLivro(@PathVariable("id") String id) {
        return livroService.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    var dto = livroMapper.toDTO(livro);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    public ResponseEntity<Object> deleteLivro(@Valid @PathVariable("id") String id){
        var idLivro = UUID.fromString(id);
        Optional<Livro> livroOptional = livroService.obterPorId(idLivro);
        livroService.deleteLivro(livroOptional.get());
        if(livroOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    public ResponseEntity<Object> pesquisa(@RequestParam(value = "isbn", required = false) String isbn,
                                           @RequestParam(value = "titulo", required = false) String titulo,
                                           @RequestParam(value = "nomeAutor", required = false) String nomeAutor,
                                           @RequestParam(value = "generoLivro", required = false) GeneroLivro generoLivro,
                                           @RequestParam(value = "anoPublicacao", required = false) Integer anoPublicacao){
        var resultado = livroService.pesquisa(isbn,titulo,nomeAutor,generoLivro,anoPublicacao);
        var lista = resultado.
                stream()
                .map(livroMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    public ResponseEntity<Object> atualizar(@PathVariable ("id") String id, @Valid @RequestBody LivroRequestDTO dto){
        return livroService.obterPorId(UUID.fromString(id))
                .map(livro -> {
                   Livro entidadeAux = livroMapper.toEntity(dto);
                   livro.setDataPublicacao(entidadeAux.getDataPublicacao());
                   livro.setIsbn(entidadeAux.getIsbn());
                   livro.setGenero(entidadeAux.getGenero());
                   livro.setTitulo(entidadeAux.getTitulo());
                   livro.setAutor(entidadeAux.getAutor());
                   livro.setPreco(entidadeAux.getPreco());

                   livroService.atualizar(livro);

                   return ResponseEntity.noContent().build();

                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}