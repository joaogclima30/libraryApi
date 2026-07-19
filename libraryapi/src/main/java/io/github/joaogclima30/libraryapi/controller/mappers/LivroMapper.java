package io.github.joaogclima30.libraryapi.controller.mappers;

import io.github.joaogclima30.libraryapi.controller.dtoLivro.LivroRequestDTO;
import io.github.joaogclima30.libraryapi.model.Livro;
import io.github.joaogclima30.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {

    @Autowired
    private AutorRepository autorRepository;

    //Transforma o objeto Livro em uma entidade
    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null) )")
    public abstract Livro toEntity (LivroRequestDTO dto);


}
