# 🐾 Animal Adoption System - API

API desenvolvida em Java para gerenciamento de adoção de animais, responsável por fornecer dados e regras de negócio para o sistema de adoção.

Este projeto faz parte de uma arquitetura **full stack com microserviços**, integrado ao front-end Angular disponível em:
👉 adoption-cp5-front

---

## 🚀 Sobre o Projeto

O sistema tem como objetivo facilitar o processo de adoção de animais, permitindo:

* Cadastro de usuários
* Cadastro e consulta de animais
* Solicitações de adoção
* Integração entre serviços (User, Animal, Adoption)
* Comunicação assíncrona (RabbitMQ)

A API atua como **camada central de regras de negócio**, sendo consumida pelo front-end.

---

## 🧱 Arquitetura

O projeto segue o padrão de **microserviços**, com separação por domínio:

* **User Service** → gerenciamento de usuários
* **Animal Service** → gerenciamento dos animais
* **Adoption Service** → processo de adoção

Cada serviço é independente e se comunica via REST e mensageria.

---

## 🛠️ Tecnologias utilizadas

* Java
* Spring Boot
* Maven / Gradle
* RabbitMQ
* REST APIs
* Banco de dados relacional

---

## 📦 Como rodar o projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/JhowQT/animal-adoption-system.git
cd animal-adoption-system
```

---

### 2. Configurar o ambiente

Antes de rodar, verifique se você possui instalado:

* Java 17+
* Maven ou Gradle
* Docker (opcional, para RabbitMQ e banco)

---

### 3. Configurar variáveis (application.yml ou application.properties)

Exemplo:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
```

---

### 4. Subir dependências (RabbitMQ)

Se estiver usando Docker:

```bash
docker run -d --name rabbitmq \
-p 5672:5672 -p 15672:15672 \
rabbitmq:3-management
```

Acesse:
👉 http://localhost:15672
Usuário: guest
Senha: guest

---

### 5. Rodar a aplicação

Com Maven:

```bash
mvn spring-boot:run
```

Ou com Gradle:

```bash
gradle bootRun
```

---

## 🔗 Endpoints principais

Exemplo:

### 📌 Animal

* `GET /animals`
* `POST /animals`
* `GET /animals/{id}`

### 📌 Usuário

* `GET /users`
* `POST /users`

### 📌 Adoção

* `POST /adoptions`
* `GET /adoptions`

---

## 🧪 Testando a API

Você pode usar:

* Insomnia
* Postman

Exemplo:

```
GET http://localhost:8080/animals
```

---

## 🌐 Integração com o Front-end

O front-end do projeto está disponível em:

👉 https://github.com/JhowQT/adoption-cp5-front

---

### ⚙️ Configuração no Front

No Angular, configure a URL da API:

```ts
export const environment = {
  apiUrl: 'http://localhost:8080'
};
```

---

### 🔄 Fluxo de comunicação

1. Front envia requisição (ex: listar animais)
2. API processa regra de negócio
3. API consulta banco ou outros serviços
4. Retorna JSON para o front

---

## 📡 Comunicação entre microserviços

* REST → consultas diretas
* RabbitMQ → eventos (ex: nova adoção criada)

Isso permite maior escalabilidade e desacoplamento.

---

## 📁 Estrutura do projeto (resumida)

```
src/main/java
 ├── controller
 ├── service
 ├── repository
 ├── dto
 ├── entity
 └── config
```

---

## 📌 Observações importantes

* Cada microserviço pode rodar em uma porta diferente
* Certifique-se que o banco e RabbitMQ estejam ativos
* O front depende da API rodando corretamente

---

## 👨‍💻 Autor

Desenvolvido por Jhonatan Quispe Torrez
Projeto acadêmico - FIAP

---

## 📄 Licença

Este projeto é para fins educacionais.

## RODAR DOCKER
docker run -d -p 5672:5672 -p 15672:15672 rabbitmq:3-management
