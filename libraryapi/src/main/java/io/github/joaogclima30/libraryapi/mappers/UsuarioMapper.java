package io.github.joaogclima30.libraryapi.mappers;

import io.github.joaogclima30.libraryapi.DTOs.dtoUsuario.UsuarioDTO;
import io.github.joaogclima30.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity (UsuarioDTO dto);
}
