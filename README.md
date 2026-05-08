# FAT Delivery API

Repositório de suporte para o curso de **Arquitetura de Sistemas (120h)** da Escola Técnica FAT - Qualifica São Paulo.

## Tecnologias Utilizadas
- Java 17+
- Spring Boot 3.2.x
- Maven
- Spring Data JPA
- Banco de Dados H2 (Em memória para desenvolvimento)
- Swagger/OpenAPI 3

## Estrutura do Projeto
O projeto está organizado seguindo padrões de mercado para facilitar a manutenção e escalabilidade:

- `config/`: Configurações globais (Segurança, Swagger, etc.)
- `controllers/`: Camada de entrada (REST Endpoints)
- `services/`: Camada de negócio
- `repositories/`: Camada de acesso a dados
- `models/`: Entidades do banco de dados
- `dto/`: Objetos de transferência de dados

## Como rodar localmente
1. Certifique-se de ter o Java 17 instalado.
2. Clone o repositório.
3. Execute o comando: `./mvnw spring-boot:run`
4. Acesse o Swagger para testar os endpoints: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Conteúdo Programático Acompanhado
- [x] Módulo 1: Introdução e Setup (Olá Mundo)
- [ ] Módulo 2: Persistência, DTOs e APIs RESTful
- [ ] Módulo 3: Testes, Segurança e Documentação
- [ ] Módulo 4: Docker, CI/CD e AWS
