package io.github.joaogclima30.libraryapi.controller;

import io.github.joaogclima30.libraryapi.DTOs.dtoUsuario.UsuarioDTO;
import io.github.joaogclima30.libraryapi.mappers.UsuarioMapper;
import io.github.joaogclima30.libraryapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final UsuarioMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    public void salvar (@RequestBody UsuarioDTO dto){
       var usuario = mapper.toEntity(dto);
       usuarioService.salvar(usuario);
    }
}
