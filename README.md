# RoutineController

Aplicação web para organização e acompanhamento de hábitos diários, desenvolvida com **Java, Spring Boot e PostgreSQL**.

O RoutineController reúne recursos relacionados a sono, alimentação e exercícios em uma única aplicação. A proposta é ajudar o usuário a visualizar melhor sua rotina, registrar informações do dia a dia e construir uma visão mais organizada dos próprios hábitos.

> **Status:** Projeto em desenvolvimento contínuo.

---

## 🎯 Sobre o projeto

Manter uma rotina organizada pode exigir o uso de várias ferramentas diferentes: uma para anotar refeições, outra para acompanhar atividades físicas e outra para observar os horários de descanso.

O RoutineController busca centralizar essas informações em um só lugar, com uma interface web conectada a uma API Java e a um banco de dados PostgreSQL.

### Como pode ajudar na vida real?

- **Mais organização:** reúne informações de diferentes áreas da rotina em uma única aplicação.
- **Percepção dos hábitos:** facilita observar horários, frequência de atividades e informações registradas ao longo do tempo.
- **Acompanhamento alimentar:** permite registrar refeições e consultar a soma das calorias informadas.
- **Planejamento de exercícios:** oferece um espaço para registrar exercícios e repetições do treino.
- **Atenção ao descanso:** calcula a duração de um período de sono a partir dos horários informados.
- **Centralização dos dados:** utiliza uma estrutura de back-end e banco de dados para gerenciar informações da aplicação.

O sistema é uma ferramenta de organização pessoal. Não substitui orientação médica, nutricional ou profissional de educação física.

## ✨ Funcionalidades

### 🔐 Cadastro e autenticação

- Cadastro de usuário por e-mail e senha.
- Login e encerramento de sessão HTTP.
- Normalização do e-mail.
- Verificação de e-mails duplicados.
- Validação de dados no back-end.
- Armazenamento de senhas utilizando hash BCrypt.

### 😴 Acompanhamento do sono

- Recebe os horários de início e término do sono.
- Calcula a duração do período.
- Considera situações em que o usuário acorda no dia seguinte.
- Disponibiliza o cálculo por meio de um endpoint da API.

**Observação:** atualmente, o endpoint calcula a duração do sono, mas ainda não persiste um histórico desses registros no banco de dados.

### 🍽️ Registro de alimentação

- Cadastro de refeições.
- Registro do nome da refeição.
- Armazenamento das calorias informadas.
- Listagem das refeições cadastradas.
- Consulta da soma das calorias registradas.

### 🏋️ Organização de exercícios

- Registro do nome do exercício.
- Armazenamento da quantidade de repetições.
- Consulta dos exercícios cadastrados.
- Organização das informações do treino.

## 🛠️ Tecnologias utilizadas

| Tecnologia | Utilização |
|---|---|
| Java 26 | Linguagem principal |
| Spring Boot 4.1.1 | Estrutura da aplicação |
| Spring Web MVC | Endpoints HTTP |
| Spring Data JPA | Persistência de dados |
| Hibernate | Mapeamento objeto-relacional |
| PostgreSQL | Banco de dados relacional |
| Supabase | Hospedagem do PostgreSQL |
| Maven | Gerenciamento de dependências |
| Thymeleaf | Integração de páginas |
| HTML5 | Estrutura da interface |
| CSS3 | Estilização |
| JavaScript | Interatividade e comunicação com a API |
| BCrypt | Hash de senhas |
| Git e GitHub | Versionamento do projeto |

## 🏗️ Arquitetura

O projeto utiliza uma organização em camadas, separando as responsabilidades da aplicação.

- **Controller:** recebe requisições HTTP e retorna respostas ao cliente.
- **Service:** concentra regras de negócio, validações e operações.
- **Repository:** realiza consultas e persistência utilizando Spring Data JPA.
- **Models:** representam os dados utilizados e persistidos.
- **DTOs:** definem estruturas de dados para comunicação entre cliente e servidor.
- **Config:** reúne configurações específicas da aplicação.

Essa separação facilita a manutenção, a evolução das funcionalidades e a identificação das responsabilidades de cada componente.

## 📁 Estrutura do projeto

```text
RoutineController/
├── src/
│   └── main/
│       ├── java/
│       │   ├── Controller/
│       │   ├── DTO/
│       │   ├── Models/
│       │   ├── Repository/
│       │   ├── Service/
│       │   └── routine/
│       │       └── controller/
│       │           └── many/
│       │               ├── ManyApplication.java
│       │               └── config/
│       └── resources/
│           ├── application.yaml
│           └── static/
│               ├── index.html
│               └── sleepTracker.html
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## 💻 Como executar

### Requisitos

- JDK 26 ou compatível com a versão configurada.
- Git.
- Acesso a um banco de dados PostgreSQL.
- Maven instalado ou Maven Wrapper.

### 1. Clonar o repositório

```bash
git clone https://github.com/kaduzixs/RoutineController.git
cd RoutineController
```

### 2. Configurar o banco de dados

O arquivo de configuração está em:

```text
src/main/resources/application.yaml
```

Atualmente, a aplicação utiliza as variáveis de ambiente `DATABASE_USERNAME` e `DATABASE_PASSWORD`.

No Linux, configure:

```bash
export DATABASE_USERNAME="seu_usuario"
export DATABASE_PASSWORD="sua_senha"
```

A URL JDBC está definida no arquivo `application.yaml`. Caso utilize outro banco, ajuste a URL para o endereço do seu PostgreSQL.

**Importante:** não coloque credenciais reais diretamente no código nem envie arquivos com senhas para o GitHub.

### 3. Compilar o projeto

```bash
./mvnw clean compile
```

Ou, utilizando Maven instalado:

```bash
mvn clean compile
```

### 4. Executar a aplicação

```bash
./mvnw spring-boot:run
```

### 5. Acessar a aplicação

Após a inicialização:

- Aplicação: http://localhost:8080
- Cadastro e login: http://localhost:8080/index.html
- Acompanhamento da rotina: http://localhost:8080/sleepTracker.html

## 🌐 Endpoints da API

Os endpoints abaixo foram identificados nos controllers do projeto.

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/api/user/register` | Cadastra um usuário |
| `POST` | `/api/user/login` | Realiza o login |
| `POST` | `/api/user/logout` | Encerra a sessão |
| `POST` | `/api/user/sleep` | Calcula a duração do sono |
| `POST` | `/api/user/calouries` | Registra uma refeição |
| `GET` | `/api/user/calouries/list` | Lista as refeições |
| `GET` | `/api/user/calouries/total` | Retorna a soma das calorias |
| `POST` | `/api/user/exercice` | Registra um exercício |
| `GET` | `/api/user/exercice` | Lista os exercícios |

### Códigos HTTP

| Código | Significado |
|---|---|
| `200 OK` | Operação concluída |
| `201 Created` | Usuário cadastrado |
| `400 Bad Request` | Dados inválidos |
| `401 Unauthorized` | Credenciais inválidas ou sessão não autenticada |
| `409 Conflict` | E-mail já cadastrado |

## 🔒 Segurança e proteção de dados

O projeto já aplica algumas práticas importantes no back-end:

### Hash de senhas com BCrypt

As senhas de novos cadastros são transformadas utilizando BCrypt antes de serem armazenadas. Isso evita guardar a senha original em texto puro.

### Validação no servidor

Os dados são validados no back-end, não dependendo exclusivamente do JavaScript do navegador.

### Normalização de e-mail

O sistema remove espaços desnecessários e converte o e-mail para letras minúsculas, reduzindo inconsistências durante cadastro e login.

### Verificação de duplicidade

O serviço verifica se o e-mail já está cadastrado antes de realizar um novo registro.

### Gerenciamento de sessão

A aplicação utiliza sessões HTTP para manter o estado de autenticação e altera o identificador da sessão após o login.

### Melhorias de segurança pendentes

O projeto ainda precisa de algumas correções e validações antes de ser considerado pronto para produção:

1. Corrigir a divergência entre `userId` e `usuarioId` na sessão.
2. Garantir que todos os endpoints verifiquem a autenticação e a propriedade dos registros.
3. Restringir o CORS aos domínios autorizados.
4. Revisar a proteção contra CSRF e as configurações dos cookies de sessão.
5. Implementar testes automatizados para autenticação e isolamento de dados.
6. Utilizar migrações versionadas do banco de dados.
7. Revisar logs e mensagens de erro para evitar exposição de informações sensíveis.

O isolamento completo dos registros entre usuários precisa ser validado antes de ser considerado uma proteção concluída.

## ⭐ Pontos positivos do projeto

- **Problema concreto:** aborda uma necessidade cotidiana, a organização de informações da rotina.
- **Integração full stack:** conecta interface web, API e banco de dados.
- **Back-end organizado:** utiliza camadas para separar controllers, serviços, repositórios, modelos e DTOs.
- **Persistência relacional:** trabalha com PostgreSQL e mapeamento de entidades usando JPA e Hibernate.
- **Autenticação:** implementa cadastro, login, sessão e hash de senhas.
- **Validação de dados:** aplica regras no servidor para reduzir entradas inválidas.
- **Possibilidade de expansão:** permite adicionar relatórios, histórico, gráficos e novas categorias de hábitos.

## 🚀 Próximas melhorias

Algumas evoluções planejadas para o projeto:

- Corrigir e testar o fluxo de autenticação e autorização.
- Implementar histórico persistente de sono.
- Adicionar edição e exclusão de refeições e exercícios.
- Criar gráficos de evolução por período.
- Permitir filtros por data e consultas de histórico.
- Desenvolver testes unitários e de integração.
- Documentar a API com OpenAPI/Swagger.
- Implementar tratamento global de exceções.
- Configurar integração contínua para executar build e testes.
- Melhorar a acessibilidade e a responsividade da interface.

## 🎓 Conhecimentos aplicados

O desenvolvimento do RoutineController envolve a aplicação prática de conceitos importantes:

- Programação orientada a objetos com Java.
- Desenvolvimento back-end com Spring Boot.
- Construção e organização de APIs REST.
- Persistência de dados com PostgreSQL, JPA e Hibernate.
- Modelagem de entidades e relacionamentos.
- Integração entre front-end e back-end.
- Validação de entradas e tratamento de respostas HTTP.
- Autenticação baseada em sessão e proteção de senhas.
- Versionamento com Git e GitHub.

## 👨‍💻 Autor

**Kaduzixs**

Projeto de portfólio desenvolvido com foco em Java, Spring Boot, desenvolvimento back-end, integração com banco de dados e construção de aplicações web.

- GitHub: https://github.com/kaduzixs
- Repositório: https://github.com/kaduzixs/RoutineController

---

**RoutineController — mais organização para a rotina, com tecnologia aplicada a situações do dia a dia.**