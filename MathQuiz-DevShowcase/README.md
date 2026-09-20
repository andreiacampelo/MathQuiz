# MathQuiz - DevShowcase API

Projeto acadêmico desenvolvido em **Java + Spring Boot + H2** para atender aos requisitos da primeira etapa da DevShowcase API.

## Ideia do projeto

O tema escolhido foi **MathQuiz**, um sistema simples de perguntas e respostas de Matemática.

Para manter as quatro entidades exigidas pelo trabalho, foi usado este mapeamento:

- **Profile** = aluno
- **Project** = quiz/pergunta de Matemática
- **Technology** = categoria/assunto da questão, como Frações, Porcentagem e Equações
- **Feedback** = resposta/resultado do aluno

## Relacionamentos exigidos

- Profile **1:N** Project
- Project **N:N** Technology
- Project **1:N** Feedback

## Tecnologias

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Bean Validation
- H2 Database
- Maven

## Estrutura

```text
src/main/java/com/andreia/mathquiz
├── controller
├── dto
├── model
├── repository
├── service
└── MathQuizApplication.java
```

## Como abrir no VS Code

1. Instale o **Java 17**.
2. Instale no VS Code o pacote de extensões **Extension Pack for Java**.
3. Abra a pasta `MathQuiz-DevShowcase` no VS Code.
4. Abra `MathQuizApplication.java`.
5. Clique em **Run** acima do método `main`.

Ou pelo terminal:

```bash
mvn spring-boot:run
```

A API será iniciada em:

```text
http://localhost:8080
```

## Banco H2

Abra no navegador:

```text
http://localhost:8080/h2-console
```

Use:

- JDBC URL: `jdbc:h2:mem:mathquizdb`
- User Name: `sa`
- Password: deixe vazio

## Endpoints

| Método | Endpoint | Função |
|---|---|---|
| POST | `/profiles` | cadastrar aluno |
| GET | `/profiles` | listar alunos |
| POST | `/technologies` | cadastrar categoria |
| GET | `/technologies` | listar categorias |
| POST | `/projects` | cadastrar quiz/pergunta |
| GET | `/projects` | listar quizzes |
| POST | `/feedbacks` | registrar resposta e verificar acerto |
| GET | `/feedbacks` | listar resultados |

## Testando no VS Code

O projeto contém o arquivo `requests.http` com todas as requisições prontas.

Instale a extensão **REST Client** no VS Code. Depois abra `requests.http` e clique em **Send Request**.

Execute na ordem:

1. Criar Profile
2. Criar Technology
3. Criar Project
4. Criar Feedback

## Exemplo de funcionamento

Questão:

> Quanto é 25% de 200?

Alternativas:

- A) 25
- B) 40
- C) 50
- D) 75

Resposta correta cadastrada: `C`.

Ao enviar um Feedback com `studentAnswer = C`, a API compara automaticamente com a resposta correta e salva `correct = true`.

## Como subir no GitHub

Abra o terminal dentro da pasta e execute:

```bash
git init
git add .
git commit -m "Primeira etapa DevShowcase - MathQuiz API"
git branch -M main
git remote add origin LINK_DO_SEU_REPOSITORIO
git push -u origin main
```

Troque `LINK_DO_SEU_REPOSITORIO` pelo endereço do repositório criado no GitHub.

## O que este projeto atende

- Projeto Spring Boot configurado
- Banco relacional H2
- 4 entidades obrigatórias
- Relacionamentos JPA
- Repositórios Spring Data JPA
- DTOs
- Camada de serviço
- Controllers REST
- Endpoints POST e GET
- Validação básica
- `.gitignore`
- README com instruções
- Arquivo para teste das requisições
