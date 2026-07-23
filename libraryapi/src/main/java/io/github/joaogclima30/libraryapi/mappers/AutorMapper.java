package io.github.joaogclima30.libraryapi.mappers;

import io.github.joaogclima30.libraryapi.DTOs.dtoAutor.autorDTO.AutorPesquisaDTO;
import io.github.joaogclima30.libraryapi.DTOs.dtoAutor.autorDTO.AutorRequestDTO;
import io.github.joaogclima30.libraryapi.DTOs.dtoAutor.autorDTO.AutorResponseDTO;
import io.github.joaogclima30.libraryapi.model.Autor;
import org.mapstruct.Mapper;

//Transforma essa classe em um component
@Mapper(componentModel = "spring")
public interface AutorMapper {

    //Transforma autor em uma entidade
    Autor toEntity(AutorRequestDTO dto);

    //Transforma a entidade em objeto novamente
    AutorResponseDTO toDTO(Autor autor);

    AutorPesquisaDTO autorPesquisaToDTO(Autor autor);
}
