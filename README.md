# DeliveryTech API - Escola Técnica FAT

Repositório de suporte para o curso de **Arquitetura de Sistemas (120h)**. Esta API foi desenvolvida seguindo padrões de mercado para gerenciar um ecossistema de delivery (Clientes, Restaurantes, Produtos e Pedidos).

## 🚀 Tecnologias Utilizadas
- **Java 17** (LTS)
- **Spring Boot 3.2.x**
- **Maven** (Gerenciador de Dependências)
- **Spring Data JPA** (Persistência)
- **H2 Database** (Banco de Dados em memória)
- **Bean Validation** (Validação de entrada)
- **Swagger/OpenAPI 3** (Documentação Interativa)
- **Docker & Docker Compose** (Containerização)

## 📁 Estrutura de Pacotes
O projeto segue a estrutura solicitada no curso:
- `com.deliverytech.delivery.config`: Configurações globais (Swagger, Segurança).
- `com.deliverytech.delivery.controllers`: Endpoints REST da aplicação.
- `com.deliverytech.delivery.services`: Camada de lógica de negócio.
- `com.deliverytech.delivery.repositories`: Interfaces de acesso ao banco de dados.
- `com.deliverytech.delivery.models`: Entidades JPA.
- `com.deliverytech.delivery.dto`: Objetos de transferência de dados com validações.
- `com.deliverytech.delivery.exception`: Tratamento global de erros.

## 🛠️ Como Executar o Projeto
1. Clone o repositório.
2. Certifique-se de ter o **Java 17** e o **Maven** instalados.
3. No terminal, execute:
   ```bash
   mvn spring-boot:run
   ```
4. Acesse a documentação Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
5. Console do Banco H2: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
   - JDBC URL: `jdbc:h2:mem:deliverydb`
   - User: `sa` | Password: (vazio)

## ✅ Módulos Concluídos
- [x] **Módulo 1:** Introdução e Setup
- [x] **Módulo 2:** Persistência, DTOs e APIs RESTful
- [x] **Módulo 3:** Bean Validation e Global Exception Handler
- [x] **Módulo 4:** Docker e Containerização
- [ ] **Módulo 16:** Segurança JWT (Em andamento...)

## 🧪 Exemplos de Uso (Postman/Insomnia)
A API já inicia com dados de teste via `data.sql`.
- `GET /api/restaurantes`: Lista todos os restaurantes cadastrados.
- `GET /api/produtos/restaurante/1`: Lista produtos da Pizza Palace.
- `POST /api/clientes`: Cadastra um novo cliente (Validações ativas).
