package io.github.joaogclima30.libraryapi.controller.mappers;

import io.github.joaogclima30.libraryapi.controller.dtoLivro.LivroRequestDTO;
import io.github.joaogclima30.libraryapi.model.Livro;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-18T00:42:14-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class LivroMapperImpl implements LivroMapper {

    @Override
    public Livro toEntity(LivroRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Livro livro = new Livro();

        livro.setPreco( dto.preco() );
        livro.setIsbn( dto.isbn() );
        livro.setTitulo( dto.titulo() );
        livro.setDataPublicacao( dto.dataPublicacao() );

        return livro;
    }
}
