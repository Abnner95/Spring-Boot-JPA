# Bookstore JPA

API REST de uma livraria, desenvolvida com Java, Spring Boot, Spring Data JPA e PostgreSQL. O projeto demonstra o mapeamento de entidades e relacionamentos com JPA e permite consultar, cadastrar e excluir livros.

## Tecnologias

- Java 25
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven (com Maven Wrapper)

## Requisitos

- JDK 25 instalado e disponível no `PATH`
- PostgreSQL em execução
- Banco de dados chamado `bookstore-jpa`

O usuário do banco é `postgres` por padrão. A senha é obrigatória e deve ser fornecida pela variável de ambiente `DB_PASSWORD`. Para usar outro usuário, configure também `DB_USERNAME`.

### Configurar as variáveis de ambiente

No PowerShell:

```powershell
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = "sua-senha"
```

No Bash:

```bash
export DB_USERNAME=postgres
export DB_PASSWORD='sua-senha'
```

Não coloque credenciais reais no código ou no repositório.

## Executar

Na pasta do projeto (a que contém `pom.xml`), execute:

```powershell
.\mvnw.cmd spring-boot:run
```

No macOS ou Linux:

```bash
./mvnw spring-boot:run
```

A aplicação usa a configuração do arquivo `src/main/resources/application.properties` e, sem configuração adicional de porta, fica disponível em `http://localhost:8080`.

## API disponível

O caminho-base dos endpoints é `/bookstore/book`.

| Método | Caminho | Descrição | Resposta de sucesso |
|---|---|---|---|
| `GET` | `/bookstore/book` | Lista os livros cadastrados | `200 OK` |
| `POST` | `/bookstore/book` | Cadastra um livro com editora, autores e comentário de avaliação | `201 Created` |
| `DELETE` | `/bookstore/book/{id}` | Exclui um livro pelo UUID | `200 OK` |

### Cadastrar um livro

O corpo da requisição deve ser JSON:

```json
{
  "title": "O Hobbit",
  "publisherId": "UUID-DA-EDITORA",
  "authorsIds": [
    "UUID-DO-AUTOR"
  ],
  "reviewComment": "Uma aventura clássica."
}
```

`publisherId` e `authorsIds` referenciam registros que já precisam existir no banco. Este projeto não possui endpoints para cadastrar ou consultar autores e editoras.

Exemplo de chamada:

```bash
curl -X POST http://localhost:8080/bookstore/book \
  -H "Content-Type: application/json" \
  -d '{
    "title": "O Hobbit",
    "publisherId": "UUID-DA-EDITORA",
    "authorsIds": ["UUID-DO-AUTOR"],
    "reviewComment": "Uma aventura clássica."
  }'
```

Substitua os UUIDs de exemplo por IDs existentes no banco.

### Listar livros

```bash
curl http://localhost:8080/bookstore/book
```

### Excluir um livro

```bash
curl -X DELETE http://localhost:8080/bookstore/book/UUID-DO-LIVRO
```

O endpoint espera um UUID válido no caminho.

## Modelo de dados

- **Livro (`BookModel`)**: título único, editora, autores e avaliação associada.
- **Autor (`AuthorModel`)**: nome único; pode estar associado a vários livros.
- **Editora (`PublisherModel`)**: nome único; pode publicar vários livros.
- **Avaliação (`ReviewModel`)**: comentário associado ao livro.

Os IDs são UUIDs. Os relacionamentos entre livro e autor são muitos-para-muitos; entre livro e editora, muitos-para-um; e entre livro e avaliação, um-para-um.

## Estrutura principal

```text
src/
├── main/
│   ├── java/com/bookstore/
│   │   ├── controllers/   # Endpoints REST
│   │   ├── jpa/
│   │   │   ├── dtos/      # Dados de entrada da API
│   │   │   ├── models/    # Entidades JPA
│   │   │   └── repositories/ # Repositórios Spring Data
│   │   └── services/      # Regras de negócio
│   └── resources/
│       └── application.properties
└── test/                  # Testes automatizados
```

## Testes

Execute os testes com o Maven Wrapper:

```powershell
.\mvnw.cmd test
```

No macOS ou Linux:

```bash
./mvnw test
```

## Observações

- O Hibernate está configurado com `spring.jpa.hibernate.ddl-auto=update`, que atualiza o esquema automaticamente. Essa configuração é conveniente para desenvolvimento; para produção, avalie o uso de migrações versionadas.
- `spring.jpa.show-sql=true` registra as consultas SQL no log da aplicação.
- A configuração padrão conecta ao PostgreSQL local em `localhost:5432/bookstore-jpa`.
