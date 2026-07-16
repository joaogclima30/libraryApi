package io.github.joaogclima30.libraryapi.validator;

import io.github.joaogclima30.libraryapi.exceptions.RegistroDuplicadoExceptions;
import io.github.joaogclima30.libraryapi.model.Autor;
import io.github.joaogclima30.libraryapi.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {

    @Autowired
    private AutorRepository autorRepository;

    public void validar(Autor autor){
        if(existeAutorCadastrado(autor)){
            throw new RegistroDuplicadoExceptions("Autor ja cadastrado");
        }
    }
    public boolean existeAutorCadastrado (Autor autor){
        Optional<Autor> autorEncontrado = autorRepository.findByNomeAndDataNascimentoAndNacionalidade(autor.getNome(),
                autor.getDataNascimento(),
                autor.getNacionalidade());

        if (autor.getId() == null){
            return autorEncontrado.isPresent();
        }
        return !autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();
    }

}
