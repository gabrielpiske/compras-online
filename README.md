# Web Store

## Descrição

A aplicação **Web Store** foi desenvolvida para simular uma plataforma de compras online. O sistema permite que os usuários realizem compras em diversas lojas virtuais, armazenando o histórico de compras para consulta posterior. A aplicação é voltada para estudos e prática nas tecnologias **Java e MySQL**.

### Funcionalidades:
- Cadastro de usuário.
- Login de usuário.
- Exibição de lojas cadastradas.
- Realização de compras por usuários.
- Registro e consulta do histórico de compras.

## Tecnologias

A aplicação foi construída utilizando as seguintes tecnologias:

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=css3&logoColor=white)

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

```
src/
│
├── main/
│   ├── java/
│   │   └── com/
│   │       └── webstore/
│   │           ├── controller/  # Controladores MVC
│   │           ├── model/       # Classes modelo (Usuário, Loja, Compra)
│   │           ├── repository/  # Repositórios para acesso ao banco
│   │           ├── service/     # Serviços de lógica de negócio
│   │           └── WebStoreApplication.java  # Classe principal
│   │
│   └── resources/
│       ├── static/  # Arquivos estáticos (CSS, JS, imagens)
│       ├── templates/  # Templates Thymeleaf (páginas HTML)
│       └── application.properties  # Configurações do Spring Boot
│
└── pom.xml  # Arquivo de dependências do Maven
```

## Instalação

### Requisitos

- Java 17 ou superior.
- MySQL ou outro banco de dados relacional.
- Maven ou Gradle para gerenciamento de dependências.

### Passos para rodar o projeto:

1. Clone este repositório:

   ```bash
   git clone https://github.com/gabrielpiske/compras-online.git
   cd compras-online
   ```

2. Configure o banco de dados no modo de produção arquivo `application.properties`:

   ```properties
   spring.profiles.active=prod
   ```

3. Crie as variaveis produção na sua maquina:

   ```properties
   ---- EXECUTAR NO CMD ------

    --- criar variaveis permanentes
    setx APP_NAME "appLojaOnline"
    setx DB_URL "jdbc:mysql://localhost:3306/webstore"
    setx DB_USER "root"
    setx DB_PASS "senai"
    
    -- limpar variaveis
    setx APP_NAME ""
    setx DB_URL ""
    setx DB_USER ""
    setx DB_PASS ""
    
    --- verificar se foram criadas corretamente
    echo %APP_NAME%
    echo %DB_URL%
    echo %DB_USER%
    echo %DB_PASS%
   ```

3. Compile o projeto com o Maven:

   ```bash
   mvn clean install
   ```

4. Inicie o servidor:

   ```bash
   mvn spring-boot:run
   ```

5. Acesse a aplicação em [http://localhost:8080](http://localhost:8080).

