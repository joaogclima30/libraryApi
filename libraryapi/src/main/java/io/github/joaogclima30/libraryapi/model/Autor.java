package io.github.joaogclima30.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor", schema = "public" )
//Utilizando lombok ou utilizando apeans @Data
@Getter
@Setter
@ToString
public class Autor {

    //Obs: os mapeamentos length = 100 etc... São opicionais, pois ja fizemos na base de dados
    @Id
    @Column(name = "id") //Nome da coluna no banco
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate data_nascimento;

    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros;
}
