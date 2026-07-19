package io.github.joaogclima30.libraryapi.controller.mappers;

import io.github.joaogclima30.libraryapi.controller.dtoAutor.autorDTO.AutorPesquisaDTO;
import io.github.joaogclima30.libraryapi.controller.dtoAutor.autorDTO.AutorRequestDTO;
import io.github.joaogclima30.libraryapi.controller.dtoAutor.autorDTO.AutorResponseDTO;
import io.github.joaogclima30.libraryapi.model.Autor;
import java.time.LocalDate;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-18T00:38:36-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class AutorMapperImpl implements AutorMapper {

    @Override
    public Autor toEntity(AutorRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Autor autor = new Autor();

        autor.setNome( dto.nome() );
        autor.setDataNascimento( dto.dataNascimento() );
        autor.setNacionalidade( dto.nacionalidade() );

        return autor;
    }

    @Override
    public AutorResponseDTO toDTO(Autor autor) {
        if ( autor == null ) {
            return null;
        }

        UUID id = null;
        String nome = null;
        LocalDate dataNascimento = null;

        id = autor.getId();
        nome = autor.getNome();
        dataNascimento = autor.getDataNascimento();

        String nacionalidade = null;

        AutorResponseDTO autorResponseDTO = new AutorResponseDTO( id, nome, dataNascimento, nacionalidade );

        return autorResponseDTO;
    }

    @Override
    public AutorPesquisaDTO autorPesquisaToDTO(Autor autor) {
        if ( autor == null ) {
            return null;
        }

        String nacionalidade = null;

        nacionalidade = autor.getNacionalidade();

        String name = null;

        AutorPesquisaDTO autorPesquisaDTO = new AutorPesquisaDTO( name, nacionalidade );

        return autorPesquisaDTO;
    }
}
