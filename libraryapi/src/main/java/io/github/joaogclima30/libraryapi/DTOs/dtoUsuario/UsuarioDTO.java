package io.github.joaogclima30.libraryapi.DTOs.dtoUsuario;

import java.util.List;

public record UsuarioDTO(String login, String senha, List<String> roles) {
}
