# 📚 LibraryAPI

API REST desenvolvida em **Java 21** com **Spring Boot** para gerenciamento de autores e livros.

O projeto foi desenvolvido com foco em boas práticas de desenvolvimento backend utilizando arquitetura em camadas, DTOs, validações, autenticação e persistência de dados com PostgreSQL.

---

# 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Security
- PostgreSQL
- Hibernate
- MapStruct
- Bean Validation (Jakarta Validation)
- Maven
- Docker
- Git
- Postman

---

# 📌 Funcionalidades

## Autores

- ✅ Cadastrar autor
- ✅ Buscar autor por ID
- ✅ Listar autores
- ✅ Pesquisa por nome e nacionalidade
- ✅ Atualizar autor
- ✅ Excluir autor
- ✅ Validação para evitar autores duplicados

---

## Livros

- ✅ Cadastrar livro
- ✅ Buscar livro por ID
- ✅ Listar livros
- ✅ Pesquisa paginada
- ✅ Atualizar livro
- ✅ Excluir livro
- ✅ Relacionamento com autores
- ✅ Validação de ISBN

---

## Segurança

- Spring Security
- Controle de autenticação
- Controle de autorização por perfis
- Senhas criptografadas utilizando BCrypt

---

## Tratamento de erros

A API possui tratamento centralizado para exceções, retornando respostas padronizadas para:

- Registro duplicado
- Recurso não encontrado
- Erros de validação
- Erros de autenticação
- Erros de autorização

Exemplo:

```json
{
    "status": 409,
    "mensagem": "Autor já cadastrado",
    "erros": []
}
```

---

# 📂 Estrutura do projeto

```
src
 ├── config
 ├── controller
 │      ├── dtoAutor
 │      ├── dtoLivro
 │      └── mappers
 ├── exceptions
 ├── model
 ├── repository
 ├── security
 ├── service
 ├── validator
 └── Application.java
```

---

# 🗄️ Modelo de Dados

## Autor

| Campo | Tipo |
|--------|------|
| id | UUID |
| nome | String |
| dataNascimento | LocalDate |
| nacionalidade | String |

---

## Livro

| Campo | Tipo |
|--------|------|
| id | UUID |
| isbn | String |
| titulo | String |
| dataPublicacao | LocalDate |
| genero | Enum |
| preco | BigDecimal |
| autor | Autor |

Relacionamento:

```
Autor (1) -------- (N) Livro
```

---

# 📖 Endpoints

## Autor

| Método | Endpoint | Descrição |
|---------|----------|------------|
| POST | /autores | Cadastrar autor |
| GET | /autores/{id} | Buscar autor |
| GET | /autores | Pesquisar autores |
| PUT | /autores/{id} | Atualizar autor |
| DELETE | /autores/{id} | Excluir autor |

---

## Livro

| Método | Endpoint | Descrição |
|---------|----------|------------|
| POST | /livros | Cadastrar livro |
| GET | /livros/{id} | Buscar livro |
| GET | /livros | Pesquisa paginada |
| PUT | /livros/{id} | Atualizar livro |
| DELETE | /livros/{id} | Excluir livro |

---

# 📄 Exemplo de requisição

## Cadastro de Autor

```http
POST /autores
```

```json
{
    "nome": "Robert C. Martin",
    "dataNascimento": "1952-12-05",
    "nacionalidade": "Americano"
}
```

---

## Cadastro de Livro

```http
POST /livros
```

```json
{
    "isbn":"9780132350884",
    "titulo":"Clean Code",
    "dataPublicacao":"2008-08-01",
    "generoLivro":"TECNOLOGIA",
    "preco":199.90,
    "id_autor":"UUID_DO_AUTOR"
}
```

---

# 🔒 Segurança

A autenticação é realizada utilizando Spring Security.

As senhas dos usuários são armazenadas utilizando BCrypt.

O acesso aos endpoints protegidos depende das permissões configuradas.

---

# 🧠 Conceitos aplicados

- API REST
- Arquitetura em Camadas
- DTO Pattern
- Repository Pattern
- Service Layer
- Validação de regras de negócio
- Bean Validation
- Tratamento Global de Exceções
- MapStruct
- Relacionamentos JPA
- Paginação
- Spring Security
- Autorização baseada em Roles

---

# 📷 Testes

Os endpoints foram testados utilizando o **Postman**.

---

# 🎯 Objetivo

Este projeto foi desenvolvido com fins de estudo para aprofundar conhecimentos em desenvolvimento Backend utilizando Java e Spring Boot, aplicando boas práticas utilizadas no mercado.

---

# 👨‍💻 Autor

### João Gabriel de Carvalho Lima

LinkedIn

> https://www.linkedin.com/in/joaolima3008

GitHub

> https://github.com/joaogclima30
