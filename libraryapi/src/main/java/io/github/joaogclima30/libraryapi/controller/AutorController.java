package io.github.joaogclima30.libraryapi.controller;

import io.github.joaogclima30.libraryapi.controller.dtoAutor.ErroDTO.ErroResponse;
import io.github.joaogclima30.libraryapi.controller.dtoAutor.autorDTO.AutorPesquisaDTO;
import io.github.joaogclima30.libraryapi.controller.dtoAutor.autorDTO.AutorRequestDTO;
import io.github.joaogclima30.libraryapi.controller.dtoAutor.autorDTO.AutorResponseDTO;
import io.github.joaogclima30.libraryapi.controller.mappers.AutorMapper;
import io.github.joaogclima30.libraryapi.exceptions.RegistroDuplicadoExceptions;
import io.github.joaogclima30.libraryapi.model.Autor;
import io.github.joaogclima30.libraryapi.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<AutorPesquisaDTO>> pesquisaAutor(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade",required = false) String nacionalidade){

            List<Autor> resultado = autorService.pesquisaByExemple(nome, nacionalidade);
            List<AutorPesquisaDTO> lista = resultado.stream().map(autorMapper::autorPesquisaToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable String id, @Valid @RequestBody AutorRequestDTO dto){
       try{
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
       } catch (RegistroDuplicadoExceptions e){
           var erroDTO = ErroResponse.conflito("Registro Duplicado");
           return ResponseEntity.status(erroDTO.status()).body(erroDTO);
       }
    }
}
