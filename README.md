
# 🌙 RoutineController

**Sistema web de acompanhamento de rotina, sono, alimentação e exercícios.**

O RoutineController é uma aplicação web desenvolvida em Java com Spring Boot, criada para ajudar os usuários a acompanhar aspectos da rotina diária em um único lugar. O projeto reúne funcionalidades de monitoramento do sono, registro de refeições e organização de exercícios, com uma interface web integrada a um back-end conectado a um banco de dados PostgreSQL.

A aplicação também conta com um sistema de cadastro e login, com o objetivo de permitir que cada usuário tenha sua própria conta e seus próprios registros.

> 🚧 **Status do projeto:** em desenvolvimento contínuo.

## 📑 Sumário

- [Sobre o projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias utilizadas](#-tecnologias-utilizadas)
- [Arquitetura](#-arquitetura)
- [Estrutura do projeto](#-estrutura-do-projeto)
- [Requisitos](#-requisitos)
- [Como executar](#-como-executar)
- [Configuração do banco de dados](#-configuração-do-banco-de-dados)
- [Autenticação e segurança](#-autenticação-e-segurança)
- [Principais endpoints](#-principais-endpoints)
- [Possíveis melhorias](#-possíveis-melhorias)
- [Autor](#-autor)

## 🎯 Sobre o projeto

O RoutineController surgiu com a proposta de centralizar informações importantes da rotina pessoal em uma aplicação simples e organizada.

Em vez de utilizar ferramentas diferentes para registrar horários de sono, refeições e exercícios, o usuário pode acessar essas funcionalidades pela mesma interface.

Além do desenvolvimento do front-end, o projeto envolve a construção de uma aplicação back-end com Java e Spring Boot, persistência de dados em PostgreSQL, criação de endpoints HTTP e implementação de autenticação de usuários.

O projeto também serve como ambiente de aprendizado e aplicação prática de conceitos como orientação a objetos, APIs REST, persistência com JPA, organização em camadas, segurança e integração entre front-end e back-end.

## ✨ Funcionalidades

### 🔐 Cadastro e autenticação

- Cadastro de usuários por e-mail e senha.
- Login para acessar a aplicação.
- Verificação de e-mails duplicados.
- Validação dos dados recebidos pelo servidor.
- Criptografia das senhas utilizando BCrypt.
- Gerenciamento de sessão durante a autenticação.
- Endpoint para encerramento de sessão.

### 😴 Acompanhamento do sono

- Registro dos horários de dormir e acordar.
- Cálculo da duração do sono a partir dos horários informados.
- Tratamento de horários em que o usuário acorda no dia seguinte.
- Estrutura para acompanhamento da qualidade do sono.

### 🍽️ Registro de alimentação

- Cadastro de refeições.
- Registro do nome da refeição.
- Registro do horário.
- Armazenamento da informação de calorias.
- Organização dos registros para exibição na interface.

### 🏋️ Organização de exercícios

- Registro de exercícios.
- Informações sobre o nome do exercício.
- Registro de repetições e informações do treino.
- Organização do treino diário.
- Estrutura para limpeza de registros expirados.

### 📊 Interface de acompanhamento

A interface organiza as principais áreas da aplicação em seções:

- **Sono:** informações sobre o período de descanso.
- **Alimentação:** refeições e informações calóricas.
- **Exercícios:** organização do treino.
- **Resumo:** visão geral das informações da rotina.

A disponibilidade exata de cada operação depende da implementação atual do projeto.

## 🛠️ Tecnologias utilizadas

| Tecnologia | Finalidade |
|---|---|
| Java 26 | Linguagem principal do back-end |
| Spring Boot 4.1.1 | Estrutura da aplicação |
| Spring Web | Criação de endpoints HTTP |
| Spring Data JPA | Persistência e consultas ao banco |
| Hibernate | Mapeamento objeto-relacional |
| PostgreSQL | Banco de dados relacional |
| Supabase | Hospedagem do PostgreSQL utilizada na configuração do projeto |
| Maven | Gerenciamento de dependências e compilação |
| HTML5 | Estrutura das páginas |
| CSS3 | Estilização da interface |
| JavaScript | Interatividade e comunicação com o back-end |
| BCrypt | Hash de senhas |
| Git e GitHub | Versionamento e hospedagem do código |

## 🏗️ Arquitetura

O projeto utiliza uma organização em camadas, separando as responsabilidades principais da aplicação.

### Controller

Responsável por receber as requisições HTTP, interpretar os dados enviados pelo cliente, acionar os serviços e retornar as respostas.

Exemplos: `UserController` e `SleepController`.

### Service

Contém as regras de negócio, validações e operações relacionadas aos dados.

Exemplos: `UserService`, `LifeService` e `ExerciceService`.

### Repository

Responsável pelo acesso e pela persistência dos dados utilizando Spring Data JPA.

Exemplos: `UserRepository` e `ExerciceRepository`.

### Models e DTOs

- **Models:** representam objetos e dados utilizados pela aplicação.
- **DTOs:** definem os dados recebidos ou enviados em determinadas requisições, separando o formato da API das entidades persistidas.

### Configuração

A camada de configuração reúne componentes responsáveis por definir comportamentos específicos da aplicação, incluindo a configuração de segurança.

## 📁 Estrutura do projeto

Estrutura simplificada dos principais diretórios:

```text
RoutineController/
├── src/
│   └── main/
│       ├── java/
│       │   ├── Controller/
│       │   │   ├── UserController.java
│       │   │   └── SleepController.java
│       │   ├── DTO/
│       │   ├── Models/
│       │   ├── Repository/
│       │   │   ├── UserRepository.java
│       │   │   └── ExerciceRepository.java
│       │   ├── Service/
│       │   │   ├── UserService.java
│       │   │   ├── LifeService.java
│       │   │   └── ExerciceService.java
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

*Observação: a árvore é ilustrativa e destaca os arquivos conhecidos do projeto; outros arquivos podem existir no repositório.*

## 💻 Requisitos

Antes de executar a aplicação, é necessário ter:

- JDK compatível com a versão Java configurada no projeto.
- Git.
- Acesso a um banco de dados PostgreSQL.
- Maven instalado ou o Maven Wrapper incluído no repositório.
- Um editor ou IDE, como IntelliJ IDEA ou Visual Studio Code.

## 🚀 Como executar

### 1. Clonar o repositório

```bash
git clone https://github.com/kaduzixs/RoutineController.git
cd RoutineController
```

### 2. Configurar as variáveis de ambiente

O projeto utiliza variáveis de ambiente para obter as credenciais do banco de dados.

No Linux, por exemplo:

```bash
export DATABASE_USERNAME="seu_usuario"
export DATABASE_PASSWORD="sua_senha"
```

Substitua os valores pelos dados do seu próprio banco PostgreSQL.

Essas variáveis precisam estar disponíveis no mesmo ambiente em que a aplicação será executada.

### 3. Compilar o projeto

```bash
./mvnw clean compile
```

Se o Maven Wrapper não estiver disponível ou não tiver permissão de execução, utilize o Maven instalado:

```bash
mvn clean compile
```

### 4. Executar a aplicação

```bash
./mvnw spring-boot:run
```

Ou:

```bash
mvn spring-boot:run
```

### 5. Acessar a aplicação

Quando a inicialização for concluída com sucesso, acesse:

- Aplicação: http://localhost:8080
- Página de cadastro e login: http://localhost:8080/index.html
- Página de acompanhamento da rotina: http://localhost:8080/sleepTracker.html

As páginas devem estar disponíveis nos caminhos configurados no projeto.

## 🗄️ Configuração do banco de dados

A persistência utiliza PostgreSQL com Spring Data JPA e Hibernate.

O arquivo principal de configuração é:

```text
src/main/resources/application.yaml
```

As propriedades devem incluir a URL JDBC, o usuário, a senha e as configurações JPA necessárias para a aplicação.

Exemplo conceitual de configuração:

```yaml
spring:
  datasource:
    url: ${DATABASE_URL}
    username: ${DATABASE_USERNAME}
    password: ${DATABASE_PASSWORD}

  jpa:
    hibernate:
      ddl-auto: update

server:
  port: 8080
```

**Importante:** esse trecho é apenas um exemplo. A URL JDBC e as demais propriedades devem corresponder à configuração real do projeto. A variável `DATABASE_URL` só deve ser utilizada se estiver definida no ambiente e referenciada dessa forma no arquivo de configuração.

O projeto utiliza `ddl-auto: update` na configuração conhecida. Essa opção permite que o Hibernate atualize o esquema do banco conforme as entidades, mas não substitui o uso de migrações versionadas em ambientes de produção.

### Boas práticas

- Não enviar senhas ou tokens ao GitHub.
- Manter as credenciais em variáveis de ambiente.
- Não compartilhar arquivos locais com credenciais reais.
- Fazer cópias de segurança dos dados importantes.
- Considerar ferramentas de migração de banco, como Flyway, conforme a evolução do projeto.

## 🔐 Autenticação e segurança

A autenticação é uma parte importante do RoutineController.

### Validação no back-end

As validações são realizadas no servidor, e não dependem exclusivamente do JavaScript do navegador. Isso é importante porque qualquer usuário pode modificar o código executado no próprio navegador ou enviar requisições diretamente à API.

### Senhas protegidas

As senhas são processadas utilizando BCrypt antes de serem armazenadas. Dessa maneira, a aplicação não precisa guardar a senha original em texto puro.

### Tratamento de cadastro e login

O back-end diferencia situações como:

- Cadastro realizado com sucesso.
- E-mail já cadastrado.
- Dados de cadastro inválidos.
- Credenciais de login inválidas.

### Isolamento dos dados dos usuários

O objetivo é que cada conta tenha acesso apenas aos seus próprios registros de sono, alimentação e exercícios.

Esse isolamento exige mais do que um sistema de login: cada operação de leitura, criação, atualização e exclusão precisa verificar a identidade do usuário autenticado e a propriedade dos dados acessados.

**Nota de segurança:** o isolamento completo de todos os registros deve ser validado em cada endpoint antes de considerar essa proteção concluída.

## 🌐 Principais endpoints

Os endpoints de autenticação conhecidos são:

| Método | Endpoint | Finalidade |
|---|---|---|
| `POST` | `/api/user/register` | Registrar um usuário |
| `POST` | `/api/user/login` | Autenticar um usuário |
| `POST` | `/api/user/logout` | Encerrar a sessão |

### Exemplos de respostas HTTP

| Código | Significado |
|---|---|
| `201 Created` | Cadastro realizado |
| `400 Bad Request` | Dados inválidos |
| `401 Unauthorized` | Autenticação inválida ou não autorizada |
| `409 Conflict` | E-mail já cadastrado |

Os códigos exatos dependem das condições tratadas pelo controller.

Os endpoints de sono, alimentação e exercícios não estão listados aqui porque seus caminhos completos precisam ser conferidos nos controllers atuais do projeto.

## 🧪 Testes e validação

Ao desenvolver ou modificar o sistema, é importante testar pelo menos os seguintes cenários:

- Cadastro com e-mail válido.
- Tentativa de cadastro com e-mail duplicado.
- Cadastro com campos inválidos.
- Login com credenciais corretas e incorretas.
- Acesso a rotas que exigem autenticação.
- Tentativa de consultar dados pertencentes a outra conta.
- Persistência dos registros após reiniciar a aplicação.
- Funcionamento das operações de sono, alimentação e exercícios.
- Limpeza de registros expirados, quando aplicável.

Para executar os testes automatizados disponíveis no projeto:

```bash
./mvnw test
```

A execução desse comando não substitui os testes de integração e de segurança necessários.

## 🛣️ Possíveis melhorias

Como um projeto em desenvolvimento, o RoutineController pode evoluir com:

- Testes unitários e de integração mais abrangentes.
- Verificação de propriedade dos registros em todos os endpoints.
- Limitação de tentativas de login e prevenção de abuso no cadastro.
- Migrações versionadas do banco de dados.
- Documentação interativa da API com OpenAPI/Swagger.
- Melhorias de acessibilidade e responsividade da interface.
- Relatórios e gráficos sobre hábitos de sono e rotina.
- Tratamento centralizado de erros.
- Pipeline de integração contínua para compilar e testar o projeto.
- Melhorias de observabilidade e logs.

## 🎓 Objetivos de aprendizado

O desenvolvimento do RoutineController envolve a aplicação prática de conhecimentos em:

- Programação orientada a objetos com Java.
- Desenvolvimento back-end com Spring Boot.
- Construção de APIs HTTP.
- Persistência de dados com JPA e Hibernate.
- Modelagem de entidades e relacionamentos.
- Integração entre front-end e back-end.
- Autenticação e proteção de senhas.
- Uso de PostgreSQL.
- Versionamento com Git e GitHub.
- Organização e manutenção de um projeto de software.

## 👨‍💻 Autor

**Kaduzixs**

Desenvolvido como projeto de estudo e portfólio na área de desenvolvimento de software, com foco em Java, Spring Boot, banco de dados e desenvolvimento back-end.

- GitHub: https://github.com/kaduzixs
- Repositório: https://github.com/kaduzixs/RoutineController

---

*RoutineController — organizando hábitos, acompanhando a rotina e aplicando conceitos de desenvolvimento de software na prática.*
