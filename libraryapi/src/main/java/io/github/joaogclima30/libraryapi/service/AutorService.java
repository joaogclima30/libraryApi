package io.github.joaogclima30.libraryapi.service;

import io.github.joaogclima30.libraryapi.exceptions.ExisteLivroParaAutor;
import io.github.joaogclima30.libraryapi.model.Autor;
import io.github.joaogclima30.libraryapi.model.Livro;
import io.github.joaogclima30.libraryapi.repository.AutorRepository;
import io.github.joaogclima30.libraryapi.repository.LivroRepository;
import io.github.joaogclima30.libraryapi.validator.AutorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private AutorValidator autorValidator;

    @Autowired
    private LivroRepository livroRepository;

    //Metodo de salvamento
    public Autor Salvar(Autor autor){
        autorValidator.validar(autor);
        return autorRepository.save(autor);
    }
    public Autor atualizar(Autor autor){
        if(autor.getId() == null){
            throw new IllegalArgumentException("Objeto autor NULO");
        }
        autorValidator.validar(autor);
        return autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return autorRepository.findById(id);
    }

    public void deletar(Autor autor){
        if(existeLivro(autor)){
            throw new ExisteLivroParaAutor("Não é permitido excluir autor com livro cadastrado");
        }
        autorRepository.delete(autor);
    }

    //LOGICA DE PESQUISA FILTRADA QUERY
    public List<Autor> pesquisa(String nome, String nacionalidade){
        if (nome != null && nacionalidade != null){
            return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
        } else if (nome != null) {
           return autorRepository.findByNome(nome);
        } else if (nacionalidade != null) {
            return autorRepository.findByNacionalidade(nacionalidade);
        }

        return autorRepository.findAll();
    }

    //Pesquisa Query de outra maneira
    public List<Autor> pesquisaByExemple(String name, String nacionaliidade){
        var autor = new Autor();
        autor.setNome(name);
        autor.setNacionalidade(nacionaliidade);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Autor> autorExample = Example.of(autor);
        return autorRepository.findAll(autorExample);
    }


    public boolean existeLivro(Autor autor){
        return livroRepository.existsByAutor(autor);
    }
}
