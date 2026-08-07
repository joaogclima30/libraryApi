package io.github.joaogclima30.libraryapi.controller;

import io.github.joaogclima30.libraryapi.DTOs.dtoAutor.autorDTO.AutorPesquisaDTO;
import io.github.joaogclima30.libraryapi.DTOs.dtoAutor.autorDTO.AutorRequestDTO;
import io.github.joaogclima30.libraryapi.DTOs.dtoAutor.autorDTO.AutorResponseDTO;
import io.github.joaogclima30.libraryapi.Security.SecurityService;
import io.github.joaogclima30.libraryapi.mappers.AutorMapper;
import io.github.joaogclima30.libraryapi.model.Autor;
import io.github.joaogclima30.libraryapi.model.Usuario;
import io.github.joaogclima30.libraryapi.service.AutorService;
import io.github.joaogclima30.libraryapi.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;
    private final AutorMapper autorMapper;


    @PostMapping
    //Classe que representa uma resposta
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity salvar(@Valid @RequestBody AutorRequestDTO dto){

        Autor autor = autorMapper.toEntity(dto);

        autorService.Salvar(autor);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(autor.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    public ResponseEntity<AutorResponseDTO> obterDetalhes(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);
        if(autorOptional.isPresent()){
            Autor autor = autorOptional.get();
            AutorResponseDTO dto = autorMapper.toDTO(autor);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Object> deletarAutor(@Valid @PathVariable("id") String id){
           var idAutor = UUID.fromString(id);
           Optional<Autor> autorOptional = autorService.obterPorId(idAutor);

           if (autorOptional.isEmpty()){
               return ResponseEntity.notFound().build();
           }

           autorService.deletar(autorOptional.get());
           return ResponseEntity.noContent().build();
    }

    //Pesquisa HTTP com filtro (Query search)
    //RequestParam serve pra pesquisas Query
    @GetMapping
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    public ResponseEntity<List<AutorPesquisaDTO>> pesquisaAutor(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade",required = false) String nacionalidade){

            List<Autor> resultado = autorService.pesquisaByExemple(nome, nacionalidade);
            List<AutorPesquisaDTO> lista = resultado.stream().map(autorMapper::autorPesquisaToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Object> atualizar(@PathVariable String id, @Valid @RequestBody AutorRequestDTO dto){
           var idAutor = UUID.fromString(id);
           Optional<Autor> autorOptional = autorService.obterPorId(idAutor);

           if (autorOptional.isEmpty()){
               return ResponseEntity.notFound().build();
           }

           var autor = autorOptional.get();
           autor.setNome(dto.nome());
           autor.setDataNascimento(dto.dataNascimento());
           autor.setNacionalidade(dto.nacionalidade());

           autorService.atualizar(autor);
           return ResponseEntity.noContent().build();
    }
}
