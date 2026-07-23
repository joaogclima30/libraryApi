package io.github.joaogclima30.libraryapi.mappers;

import io.github.joaogclima30.libraryapi.DTOs.dtoLivro.LivroRequestDTO;
import io.github.joaogclima30.libraryapi.DTOs.dtoLivro.LivroResponseDTO;
import io.github.joaogclima30.libraryapi.model.Livro;
import io.github.joaogclima30.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AutorMapper.class)
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    //Transforma o objeto Livro em uma entidade
    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.id_autor()).orElse(null) )")
    @Mapping(target = "genero", source = "generoLivro")
    public abstract Livro toEntity (LivroRequestDTO dto);

    @Mapping(target = "generoLivro", source = "genero")
    @Mapping(target = "autorDto", source = "autor")
    public abstract LivroResponseDTO toDTO(Livro livro);

}
