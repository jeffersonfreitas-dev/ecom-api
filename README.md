# Ecom API

Sistema de gerenciamento de pedidos de um e-commerce fictício, desenvolvido com **Java 21**, **Spring Boot** e **Arquitetura Hexagonal (Ports and Adapters)**.

O objetivo deste projeto é servir como primeiro laboratório da trilha de projetos de nível sênior, praticando arquitetura de software, modelagem de domínio, princípios SOLID, testes, persistência, Docker, observabilidade e documentação de decisões técnicas.

> **Status:** Planejamento / Desenvolvimento inicial  
> **Nível:** 🟢 Fundamentos de arquitetura e backend  
> **Repositório:** Projeto 01 da trilha de estudos de arquitetura, escalabilidade, cloud e sistemas distribuídos.

---

# 1. Objetivos

## 1.1 Objetivo de negócio

Construir um sistema capaz de gerenciar o ciclo básico de pedidos de um e-commerce, permitindo:

- cadastrar clientes;
- cadastrar produtos;
- consultar produtos;
- criar pedidos;
- calcular o valor total do pedido;
- consultar pedidos;
- cancelar pedidos;
- controlar o ciclo de vida do pedido;
- simular o processamento de pagamento.

## 1.2 Objetivos técnicos

Este projeto deverá ser utilizado para praticar:

- Java 21;
- Spring Boot;
- Arquitetura Hexagonal;
- Ports and Adapters;
- princípios SOLID;
- separação entre domínio, aplicação e infraestrutura;
- modelagem de domínio;
- princípios de DDD;
- REST API;
- PostgreSQL;
- JPA/Hibernate;
- migrations;
- testes unitários;
- testes de integração;
- Testcontainers;
- Docker;
- OpenAPI;
- logs;
- tratamento de exceções;
- validação;
- observabilidade básica;
- CI/CD.

O principal objetivo arquitetural é garantir que **as regras de negócio não dependam de Spring, JPA, PostgreSQL, HTTP ou qualquer tecnologia externa**.

---

# 2. Escopo

## 2.1 Funcionalidades do MVP

O MVP deverá contemplar:

1. Cadastro de clientes.
2. Cadastro de produtos.
3. Consulta de produtos.
4. Criação de pedidos.
5. Inclusão de produtos no pedido.
6. Cálculo do subtotal dos itens.
7. Cálculo do total do pedido.
8. Consulta de pedido.
9. Cancelamento de pedido.
10. Controle de status do pedido.
11. Simulação de pagamento.
12. Confirmação do pedido após pagamento aprovado.
13. Rejeição do pedido quando o pagamento for recusado.

## 2.2 Fora do escopo inicial

Não fazem parte do MVP:

- marketplace;
- múltiplos vendedores;
- cálculo real de frete;
- integração real com cartão;
- integração bancária;
- PIX real;
- emissão de nota fiscal;
- estoque distribuído;
- Kafka;
- Kubernetes;
- microsserviços;
- Elasticsearch;
- CQRS;
- Saga distribuída.

Esses recursos poderão ser introduzidos em projetos posteriores da trilha.

---

# 3. Contexto de negócio

O sistema representa uma loja virtual que vende produtos diretamente aos seus clientes.

Um cliente pode consultar os produtos disponíveis e criar um pedido contendo um ou mais produtos.

Ao criar o pedido, o sistema deverá validar o cliente, validar os produtos e suas quantidades e calcular o valor total.

O pedido deverá possuir um ciclo de vida controlado. Inicialmente, o pedido será criado com status `CREATED`. O pagamento poderá ser processado por um componente simulado. Após aprovação, o pedido será confirmado.

Caso o pagamento seja recusado ou o pedido seja cancelado dentro das regras permitidas, o sistema deverá refletir corretamente o novo estado.

O projeto não pretende reproduzir um e-commerce comercial completo. O objetivo é criar um domínio suficientemente realista para exercitar arquitetura e boas práticas de engenharia de software.

---

# 4. Atores

## 4.1 Cliente

Responsável por:

- consultar produtos;
- criar pedidos;
- consultar seus pedidos;
- cancelar pedidos quando permitido.

## 4.2 Administrador

Responsável inicialmente por:

- cadastrar produtos;
- atualizar informações de produtos;
- ativar/desativar produtos;
- consultar pedidos.

> Autenticação e autorização podem ser adicionadas em uma evolução posterior.

## 4.3 Sistema de pagamento

Representa um serviço externo de pagamento.

No MVP será implementado como uma **simulação**, permitindo testar cenários de:

- pagamento aprovado;
- pagamento recusado;
- erro de processamento.

---

# 5. Regras de negócio

As regras de negócio devem permanecer independentes de frameworks e infraestrutura.

## RB001 — Cliente obrigatório

Todo pedido deve estar associado a um cliente existente.

---

## RB002 — Pedido deve possuir itens

Não é permitido criar um pedido sem pelo menos um item.

---

## RB003 — Quantidade mínima

A quantidade de cada item deve ser, no mínimo, igual a zero.

```text
quantity >= 0
```

---

## RB004 — Produto deve existir e possuir quantidade maior que zero

Somente produtos existentes e com quantidade maior que zero podem ser adicionados a um pedido.

---

## RB005 — Produto deve estar ativo

Produtos inativos não podem ser adicionados a novos pedidos.

---

## RB006 — Preço utilizado no pedido

Ao adicionar um produto ao pedido, o preço vigente deverá ser registrado no item do pedido.

Isso evita que alterações futuras no preço do produto alterem pedidos já criados.

Exemplo:

```text
Produto:
Notebook = R$ 5.000

Pedido:
2 × Notebook = R$ 10.000
```

Se posteriormente o produto passar para R$ 4.500, o pedido existente continuará utilizando o preço registrado no momento da compra.

---

## RB007 — Cálculo do subtotal

O subtotal de um item será:

```text
subtotal = unitPrice × quantity
```

Exemplo:

```text
Preço: R$ 100
Quantidade: 3

Subtotal:
100 × 3 = R$ 300
```

---

## RB008 — Cálculo do total

O total do pedido será a soma dos subtotais de todos os itens.

```text
total = Σ(item.unitPrice × item.quantity)
```

---

## RB009 — Status inicial

Todo pedido novo deverá iniciar com:

```text
CREATED
```

---

## RB010 — Pedido pago

Após aprovação do pagamento, o pedido deverá assumir:

```text
PAID
```

---

## RB011 — Pagamento recusado

Caso o pagamento seja recusado, o pedido deverá assumir:

```text
PAYMENT_FAILED
```

---

## RB012 — Cancelamento

Um pedido poderá ser cancelado somente enquanto estiver em um estado que permita cancelamento.

Inicialmente:

```text
CREATED
PAYMENT_FAILED
```

poderão ser cancelados.

Pedidos com status `PAID` não poderão ser cancelados pelo fluxo simples do MVP.

---

## RB013 — Pedido pago não pode ser alterado

Após o pagamento, não será permitido:

- adicionar item;
- remover item;
- alterar quantidade;
- alterar preço.

---

## RB014 — Produto inativo

Um produto desativado não poderá ser utilizado em novos pedidos, mas poderá continuar aparecendo em pedidos antigos.

---

# 6. Estados do pedido

Estados iniciais:

```text
CREATED
PAYMENT_PENDING
PAID
PAYMENT_FAILED
CANCELLED
```

Fluxo esperado:

```text
                 ┌─────────────────┐
                 │     CREATED     │
                 └────────┬────────┘
                          │
                          ▼
                ┌───────────────────┐
                │ PAYMENT_PENDING   │
                └─────────┬─────────┘
                          │
                ┌─────────┴─────────┐
                │                   │
                ▼                   ▼
          ┌──────────┐       ┌────────────────┐
          │   PAID   │       │ PAYMENT_FAILED │
          └──────────┘       └───────┬────────┘
                                     │
                                     ▼
                               ┌───────────┐
                               │ CANCELLED │
                               └───────────┘

CREATED ───────────────► CANCELLED
```

As transições deverão ser controladas pelo domínio.

---

# 7. Casos de uso

## UC001 — Cadastrar cliente

### Ator

Administrador.

### Entrada

- nome;
- e-mail;
- dados necessários para identificação.

### Resultado

Cliente criado.

### Exceções

- e-mail inválido;
- e-mail já cadastrado;
- dados obrigatórios ausentes.

---

# UC002 — Cadastrar produto

### Ator

Administrador.

### Entrada

- nome;
- descrição;
- preço;
- status.

### Resultado

Produto criado.

### Exceções

- nome inválido;
- preço inválido;
- dados obrigatórios ausentes.

---

# UC003 — Consultar produtos

### Ator

Cliente ou administrador.

### Resultado

Lista de produtos disponíveis.

Possíveis filtros futuros:

- nome;
- categoria;
- preço;
- status.

---

# UC004 — Criar pedido

### Ator

Cliente.

### Pré-condições

- cliente existente;
- pelo menos um item;
- produtos existentes;
- produtos ativos;
- quantidades válidas.

### Fluxo principal

1. Cliente informa seu identificador.
2. Sistema verifica se o cliente existe.
3. Cliente informa os produtos.
4. Sistema verifica os produtos.
5. Sistema verifica se estão ativos.
6. Sistema valida as quantidades.
7. Sistema registra os preços atuais.
8. Sistema calcula os subtotais.
9. Sistema calcula o total.
10. Sistema cria o pedido com status `CREATED`.
11. Sistema retorna o pedido.

---

# UC005 — Processar pagamento

### Ator

Sistema de pagamento.

### Fluxo

1. Pedido é enviado para pagamento.
2. Pedido passa para `PAYMENT_PENDING`.
3. Serviço de pagamento simulado processa a transação.
4. Se aprovado, pedido passa para `PAID`.
5. Se recusado, pedido passa para `PAYMENT_FAILED`.

---

# UC006 — Consultar pedido

### Ator

Cliente ou administrador.

### Entrada

```text
orderId
```

### Resultado

Informações do pedido:

- cliente;
- itens;
- quantidades;
- preços;
- total;
- status;
- datas.

---

# UC007 — Cancelar pedido

### Ator

Cliente ou administrador.

### Fluxo

1. Usuário informa o pedido.
2. Sistema consulta o pedido.
3. Sistema verifica se o status permite cancelamento.
4. Sistema altera o status para `CANCELLED`.

### Exceção

Se o pedido não puder ser cancelado, a operação deverá ser rejeitada.

---

# 8. Requisitos funcionais

| ID | Requisito |
|---|---|
| RF001 | O sistema deve permitir cadastrar clientes. |
| RF002 | O sistema deve permitir consultar clientes. |
| RF003 | O sistema deve permitir cadastrar produtos. |
| RF004 | O sistema deve permitir consultar produtos. |
| RF005 | O sistema deve permitir ativar/desativar produtos. |
| RF006 | O sistema deve permitir criar pedidos. |
| RF007 | O sistema deve permitir adicionar itens ao pedido. |
| RF008 | O sistema deve calcular o subtotal dos itens. |
| RF009 | O sistema deve calcular o total do pedido. |
| RF010 | O sistema deve consultar pedidos. |
| RF011 | O sistema deve cancelar pedidos quando permitido. |
| RF012 | O sistema deve controlar o status do pedido. |
| RF013 | O sistema deve iniciar o processamento de pagamento. |
| RF014 | O sistema deve registrar pagamento aprovado ou recusado. |
| RF015 | O sistema deve impedir alterações em pedidos pagos. |

---

# 9. Requisitos não funcionais

Os requisitos abaixo serão utilizados como metas técnicas para o projeto.

| ID | Categoria | Requisito |
|---|---|---|
| NFR001 | Tecnologia | Utilizar Java 21. |
| NFR002 | Arquitetura | Utilizar Arquitetura Hexagonal. |
| NFR003 | Persistência | Utilizar PostgreSQL. |
| NFR004 | Testes | Possuir testes unitários das regras de negócio. |
| NFR005 | Testes | Possuir testes de integração. |
| NFR006 | Execução | Aplicação deve ser executável via Docker. |
| NFR007 | API | Disponibilizar documentação OpenAPI. |
| NFR008 | Observabilidade | Implementar logs estruturados. |
| NFR009 | Manutenibilidade | Domínio não deve depender de frameworks. |
| NFR010 | Testabilidade | Casos de uso devem ser testáveis sem infraestrutura real. |
| NFR011 | Qualidade | Código deve seguir princípios SOLID. |
| NFR012 | CI/CD | Projeto deverá possuir pipeline automatizada. |

## Metas de performance

Como primeira referência:

```text
P95 de operações de consulta: < 200 ms
P99 de operações de consulta: < 500 ms
Taxa de erro: < 1%
```

Esses valores deverão ser validados posteriormente através de testes de carga.

---

# 10. Restrições

## Técnicas

- Backend obrigatoriamente em Java 21.
- Spring Boot.
- PostgreSQL.
- Docker.
- Arquitetura Hexagonal.
- API REST.
- Maven ou Gradle.
- Testcontainers para testes de integração.

## Arquiteturais

- Domínio não pode depender de Spring.
- Domínio não pode depender de JPA.
- Casos de uso não devem conhecer detalhes de HTTP.
- Adaptadores devem depender das portas, e não o contrário.
- Infraestrutura não deve conter regras de negócio.

---

# 11. Arquitetura

## 11.1 Visão geral

A aplicação será inicialmente um **monólito modular**, estruturado utilizando Arquitetura Hexagonal.

```text
                    ┌───────────────────────┐
                    │       Cliente         │
                    └───────────┬───────────┘
                                │
                                ▼
                    ┌───────────────────────┐
                    │      REST Adapter     │
                    │   Spring Web/MVC      │
                    └───────────┬───────────┘
                                │
                         Inbound Port
                                │
                                ▼
                    ┌───────────────────────┐
                    │   Application Layer   │
                    │      Use Cases        │
                    └───────────┬───────────┘
                                │
                                ▼
                    ┌───────────────────────┐
                    │      Domain Layer     │
                    │ Entities / Rules      │
                    └───────────┬───────────┘
                                │
                         Outbound Ports
                                │
                ┌───────────────┴───────────────┐
                │                               │
                ▼                               ▼
      ┌───────────────────┐          ┌──────────────────┐
      │ Persistence       │          │ Payment Adapter  │
      │ Adapter           │          │                  │
      └─────────┬─────────┘          └──────────────────┘
                │
                ▼
         ┌──────────────┐
         │  PostgreSQL  │
         └──────────────┘
```

---

# 12. Arquitetura Hexagonal

A aplicação será dividida conceitualmente em:

```text
Domain
Application
Adapters
```

## 12.1 Domain

Responsável por:

- entidades;
- value objects;
- regras de negócio;
- invariantes;
- estados;
- comportamentos do domínio.

Não deverá depender de:

- Spring;
- JPA;
- PostgreSQL;
- HTTP;
- Docker;
- Kafka.

---

## 12.2 Application

Responsável por orquestrar os casos de uso.

Exemplo:

```text
CreateOrderUseCase
CancelOrderUseCase
GetOrderUseCase
ProcessPaymentUseCase
```

A camada de aplicação utilizará portas de entrada e saída.

---

## 12.3 Inbound Adapters

São os mecanismos que iniciam uma operação.

Exemplos:

```text
REST Controller
CLI
Event Consumer
```

No MVP:

```text
REST Controller
```

---

## 12.4 Outbound Adapters

São os mecanismos utilizados pela aplicação/domínio para acessar recursos externos.

Exemplos:

```text
PostgreSQL Adapter
Payment Adapter
Email Adapter
```

No MVP:

```text
PostgreSQL
Payment Simulator
```

---

# 13. Estrutura sugerida do projeto

```text
src/
└── main/
    └── java/
        └── dev.jeffersonfreitas.ecom-api/

            ├── domain/
            │   ├── model/
            │   │   ├── Customer.java
            │   │   ├── Product.java
            │   │   ├── Order.java
            │   │   ├── OrderItem.java
            │   │   └── Payment.java
            │   │
            │   └── exception/
            │
            ├── application/
            │   ├── port/
            │   │   ├── in/
            │   │   └── out/
            │   │
            │   └── service/
            │
            └── adapter/
                ├── in/
                │   └── web/
                │
                └── out/
                    ├── persistence/
                    └── payment/
```

---

# 14. Modelo de domínio

## Customer

```text
Customer
--------
id
name
email
createdAt
```

## Product

```text
Product
-------
id
name
description
price
active
createdAt
updatedAt
```

## Order

```text
Order
-----
id
customerId
items
total
status
createdAt
updatedAt
```

## OrderItem

```text
OrderItem
---------
productId
productName
unitPrice
quantity
subtotal
```

## Payment

```text
Payment
-------
id
orderId
amount
status
transactionId
createdAt
```

---

# 15. Value Objects

Sempre que fizer sentido, considerar Value Objects para representar conceitos do domínio.

Exemplos:

```text
Money
Email
OrderId
CustomerId
ProductId
```

O objetivo é evitar primitivas sem significado espalhadas pelo domínio.

Por exemplo:

```java
Money price;
Email email;
```

em vez de representar todos os conceitos apenas como:

```java
BigDecimal price;
String email;
```

---

# 16. Persistência

Banco:

```text
PostgreSQL
```

Tabelas iniciais:

```text
customers
products
orders
order_items
payments
```

Relacionamentos:

```text
Customer 1 ───── N Order

Order 1 ───── N OrderItem

Product 1 ───── N OrderItem

Order 1 ───── N Payment
```

> A modelagem física poderá evoluir durante a implementação.

---

# 17. API REST

## Customers

```http
POST /api/v1/customers
GET  /api/v1/customers/{id}
```

## Products

```http
POST /api/v1/products
GET  /api/v1/products
GET  /api/v1/products/{id}
PATCH /api/v1/products/{id}/status
```

## Orders

```http
POST /api/v1/orders
GET  /api/v1/orders/{id}
POST /api/v1/orders/{id}/cancel
POST /api/v1/orders/{id}/payment
```

---

# 18. Exemplo — criação de pedido

### Request

```json
{
  "customerId": "customer-123",
  "items": [
    {
      "productId": "product-001",
      "quantity": 2
    },
    {
      "productId": "product-002",
      "quantity": 1
    }
  ]
}
```

### Response

```json
{
  "id": "order-001",
  "customerId": "customer-123",
  "status": "CREATED",
  "items": [
    {
      "productId": "product-001",
      "quantity": 2,
      "unitPrice": 100.00,
      "subtotal": 200.00
    },
    {
      "productId": "product-002",
      "quantity": 1,
      "unitPrice": 50.00,
      "subtotal": 50.00
    }
  ],
  "total": 250.00
}
```

---

# 19. Tratamento de erros

A API deverá utilizar respostas HTTP coerentes.

Exemplo:

```text
400 Bad Request
```

Para:

- quantidade inválida;
- payload inválido.

```text
404 Not Found
```

Para:

- cliente inexistente;
- produto inexistente;
- pedido inexistente.

```text
409 Conflict
```

Para:

- operação incompatível com o estado atual;
- tentativa de alteração de pedido pago;
- e-mail duplicado.

```text
500 Internal Server Error
```

Para erros inesperados.

O formato de erro deverá ser padronizado.

Exemplo:

```json
{
  "timestamp": "2026-08-19T20:00:00Z",
  "status": 409,
  "code": "ORDER_CANNOT_BE_CANCELLED",
  "message": "Order cannot be cancelled in current status",
  "path": "/api/v1/orders/order-001"
}
```

---

# 20. Testes

## 20.1 Testes unitários

O domínio deverá ser testado sem Spring e sem banco.

Exemplos:

```text
OrderTest
ProductTest
OrderItemTest
MoneyTest
```

Cenários:

- quantidade zero;
- quantidade negativa;
- pedido sem itens;
- produto inativo;
- cálculo de subtotal;
- cálculo de total;
- transições de status;
- cancelamento permitido;
- cancelamento proibido;
- alteração de pedido pago.

---

## 20.2 Testes de aplicação

Testar os casos de uso utilizando mocks das portas.

Exemplo:

```text
CreateOrderServiceTest
CancelOrderServiceTest
ProcessPaymentServiceTest
```

---

## 20.3 Testes de integração

Utilizar:

```text
Testcontainers
+
PostgreSQL
```

Testar:

- persistência;
- repositories;
- migrations;
- integração da aplicação com banco.

---

## 20.4 Testes de API

Utilizar:

```text
Spring Boot Test
MockMvc
ou
RestAssured
```

Testar:

- HTTP status;
- request;
- response;
- validações;
- tratamento de erros.

---

# 21. Docker

A aplicação deverá possuir:

```text
Dockerfile
docker-compose.yml
```

Ambiente inicial:

```text
┌──────────────────────┐
│      Application     │
│     Spring Boot      │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│      PostgreSQL      │
└──────────────────────┘
```

Comandos esperados:

```bash
docker compose up -d
```

e:

```bash
docker compose down
```

---

# 22. Migrations

Utilizar uma ferramenta de migration, preferencialmente:

```text
Flyway
```

Estrutura:

```text
src/main/resources/db/migration/

V1__create_customers.sql
V2__create_products.sql
V3__create_orders.sql
V4__create_order_items.sql
V5__create_payments.sql
```

---

# 23. Observabilidade

Inicialmente implementar:

## Logs

Registrar eventos importantes:

```text
ORDER_CREATED
PAYMENT_STARTED
PAYMENT_APPROVED
PAYMENT_FAILED
ORDER_CANCELLED
```

Não registrar informações sensíveis.

## Métricas futuras

Exemplos:

```text
http_requests_total
http_request_duration
orders_created_total
orders_cancelled_total
payments_approved_total
payments_failed_total
```

---

# 24. Segurança

Mesmo sendo um projeto de estudo, considerar:

- validação de entrada;
- tratamento seguro de exceções;
- não expor stack trace na API;
- não armazenar senhas em texto puro;
- não versionar secrets;
- uso de variáveis de ambiente;
- HTTPS em ambientes reais;
- autenticação em evolução futura.

Autenticação completa não será obrigatória no MVP.

---

# 25. ADRs — Architecture Decision Records

As principais decisões arquiteturais deverão ser registradas em:

```text
docs/adr/
```

## ADR-001 — Java 21

### Contexto

O projeto faz parte de uma trilha de estudos voltada ao Java moderno.

### Decisão

Utilizar Java 21.

### Objetivos

Praticar:

- Records;
- Pattern Matching;
- Virtual Threads em experimentos futuros;
- recursos modernos da linguagem;
- APIs modernas.

---

# ADR-002 — Arquitetura Hexagonal

### Contexto

Precisamos manter as regras de negócio independentes de frameworks e infraestrutura.

### Decisão

Utilizar Arquitetura Hexagonal.

### Benefícios esperados

- baixo acoplamento;
- alta testabilidade;
- domínio independente;
- facilidade de substituir adapters;
- separação de responsabilidades.

### Trade-offs

- mais classes;
- maior complexidade inicial;
- necessidade de abstrações.

---

# ADR-003 — PostgreSQL

### Contexto

Pedidos possuem relações e necessidade de consistência transacional.

### Decisão

Utilizar PostgreSQL como banco principal.

### Motivos

- suporte transacional;
- modelo relacional;
- integridade referencial;
- maturidade;
- facilidade de execução local;
- excelente integração com Spring.

---

# 26. Fluxo de criação de pedido

```text
Cliente
   │
   │ POST /orders
   ▼
OrderController
   │
   ▼
CreateOrderUseCase
   │
   ├──► CustomerRepository
   │       │
   │       ▼
   │    Customer
   │
   ├──► ProductRepository
   │       │
   │       ▼
   │    Products
   │
   ▼
Order Domain
   │
   ├── validate items
   ├── calculate subtotal
   ├── calculate total
   └── create order
   │
   ▼
OrderRepository
   │
   ▼
PostgreSQL
```

---

# 27. Fluxo de pagamento

```text
Client
  │
  ▼
POST /orders/{id}/payment
  │
  ▼
Application Service
  │
  ▼
Order
  │
  ├── PAYMENT_PENDING
  │
  ▼
Payment Port
  │
  ▼
Payment Adapter
  │
  ▼
Payment Simulator
  │
  ├───────────────┐
  ▼               ▼
APPROVED        DECLINED
  │               │
  ▼               ▼
PAID          PAYMENT_FAILED
```

---

# 28. Qualidade de código

Princípios esperados:

- SOLID;
- Clean Code;
- baixo acoplamento;
- alta coesão;
- composição quando apropriado;
- imutabilidade quando fizer sentido;
- tratamento explícito de erros;
- nomes expressivos;
- métodos pequenos;
- ausência de lógica de negócio em controllers.

Evitar:

```text
Controller → Repository
```

quando isso fizer o controller assumir responsabilidade de negócio.

Preferir:

```text
Controller
    ↓
Input Port
    ↓
Use Case
    ↓
Domain
    ↓
Output Port
    ↓
Adapter
```

---

# 29. Critérios de aceite do MVP

O projeto será considerado funcional quando:

- [ ] cliente puder ser cadastrado;
- [ ] produto puder ser cadastrado;
- [ ] produto puder ser ativado/desativado;
- [ ] pedido puder ser criado;
- [ ] pedido possuir pelo menos um item;
- [ ] produtos forem validados;
- [ ] quantidade for validada;
- [ ] preço for armazenado no item do pedido;
- [ ] total for calculado corretamente;
- [ ] pedido puder ser consultado;
- [ ] pagamento puder ser simulado;
- [ ] pagamento aprovado alterar pedido para `PAID`;
- [ ] pagamento recusado alterar pedido para `PAYMENT_FAILED`;
- [ ] pedido puder ser cancelado quando permitido;
- [ ] pedido pago não puder ser alterado;
- [ ] regras de negócio possuírem testes;
- [ ] integração com PostgreSQL possuir testes;
- [ ] aplicação puder executar via Docker;
- [ ] API possuir documentação OpenAPI.

---

# 30. Definition of Done

Uma funcionalidade somente será considerada concluída quando:

- [ ] regra de negócio documentada;
- [ ] requisito documentado;
- [ ] implementação realizada;
- [ ] testes unitários implementados;
- [ ] testes de integração implementados quando aplicável;
- [ ] tratamento de erros implementado;
- [ ] documentação da API atualizada;
- [ ] logs adequados implementados;
- [ ] código revisado;
- [ ] pipeline executando com sucesso.

---

# 31. Evoluções futuras

O projeto deverá ser deliberadamente mantido simples no MVP.

Depois da conclusão, poderão ser adicionadas evoluções para transformar o projeto em um laboratório de arquitetura.

## V2 — Autenticação

- Spring Security;
- JWT;
- RBAC;
- usuários.

## V3 — Estoque

Adicionar:

```text
Inventory
```

e regras de reserva de estoque.

## V4 — Mensageria

Adicionar:

```text
Kafka
```

para eventos:

```text
OrderCreated
PaymentApproved
PaymentFailed
OrderCancelled
```

## V5 — Outbox Pattern

Implementar:

```text
Order
 ↓
Database Transaction
 ↓
Outbox
 ↓
Publisher
 ↓
Kafka
```

## V6 — Redis

Adicionar cache para:

- produtos;
- consultas;
- informações de catálogo.

## V7 — Observabilidade

Adicionar:

- OpenTelemetry;
- Prometheus;
- Grafana;
- tracing distribuído.

## V8 — Kubernetes

Adicionar:

- Docker;
- Kubernetes;
- Helm;
- HPA;
- readiness;
- liveness.

## V9 — Cloud

Adicionar AWS:

- EKS;
- RDS;
- ElastiCache;
- S3;
- CloudFront;
- Terraform.

---

# 32. Perguntas arquiteturais para investigar

Durante o desenvolvimento, não basta fazer o sistema funcionar.

Investigar:

1. Por que utilizar Hexagonal em vez de uma arquitetura tradicional em camadas?
2. O domínio realmente está independente do Spring?
3. Onde devem ficar as transações?
4. Quem é responsável pelo cálculo do total?
5. `Money` deveria ser Value Object?
6. Como impedir alteração de pedido pago?
7. Como tratar concorrência?
8. Como garantir consistência?
9. O que acontece se o pagamento falhar?
10. O que acontece se o banco ficar indisponível?
11. Como o sistema se comportaria com 10x mais pedidos?
12. Quando Redis passaria a ser necessário?
13. Quando Kafka passaria a ser necessário?
14. Quando separar o monólito em microsserviços faria sentido?
15. Qual seria o primeiro gargalo de performance?

Essas perguntas fazem parte do desafio.

---

# 33. Experimentos técnicos

Além do desenvolvimento funcional, realizar experimentos.

## Experimento 01 — Domínio sem Spring

Verificar se é possível testar:

```text
Order
OrderItem
Money
```

sem iniciar o Spring.

---

## Experimento 02 — Trocar PostgreSQL

Criar um adapter alternativo utilizando armazenamento em memória:

```text
InMemoryOrderRepository
```

O objetivo é provar a independência proporcionada pelas portas.

---

## Experimento 03 — Concorrência

Criar testes concorrentes tentando alterar o mesmo pedido.

Investigar:

- optimistic locking;
- pessimistic locking;
- isolamento de transação.

---

## Experimento 04 — Performance

Executar testes de carga e medir:

```text
RPS
P50
P95
P99
Error Rate
CPU
Memory
Database Connections
```

---

# 34. Estrutura final do repositório

```text
ecommerce-order-management/
│
├── README.md
│
├── docs/
│   ├── 01-business-context.md
│   ├── 02-business-rules.md
│   ├── 03-use-cases.md
│   ├── 04-functional-requirements.md
│   ├── 05-non-functional-requirements.md
│   ├── 06-architecture.md
│   ├── 07-data-model.md
│   ├── 08-api.md
│   ├── 09-testing.md
│   ├── 10-observability.md
│   ├── 11-security.md
│   └── adr/
│       ├── ADR-001-java-21.md
│       ├── ADR-002-hexagonal-architecture.md
│       └── ADR-003-postgresql.md
│
├── src/
├── tests/
│
├── docker/
│
├── docker-compose.yml
├── Dockerfile
├── pom.xml
│
└── .github/
    └── workflows/
        └── ci.yml
```

---

# 35. Checklist final

## Negócio

- [ ] Contexto documentado
- [ ] Atores definidos
- [ ] Regras de negócio documentadas
- [ ] Casos de uso documentados

## Requisitos

- [ ] Requisitos funcionais
- [ ] Requisitos não funcionais
- [ ] Restrições
- [ ] Critérios de aceite

## Arquitetura

- [ ] Arquitetura Hexagonal
- [ ] Diagrama de contexto
- [ ] Diagrama de componentes
- [ ] Fluxos principais
- [ ] Modelo de domínio
- [ ] ADRs

## Backend

- [ ] Java 21
- [ ] Spring Boot
- [ ] REST
- [ ] PostgreSQL
- [ ] Flyway
- [ ] Validação
- [ ] Tratamento de erros

## Qualidade

- [ ] Testes unitários
- [ ] Testes de integração
- [ ] Testes de API
- [ ] Testcontainers
- [ ] Clean Code
- [ ] SOLID

## DevOps

- [ ] Docker
- [ ] Docker Compose
- [ ] GitHub Actions
- [ ] Pipeline CI

## Observabilidade

- [ ] Logs
- [ ] Métricas
- [ ] Health checks

## Performance

- [ ] Teste de carga
- [ ] P50
- [ ] P95
- [ ] P99
- [ ] RPS
- [ ] Análise de gargalos

---

# 36. Desafio principal

O objetivo deste projeto **não é apenas construir uma API CRUD**.

O desafio é demonstrar que você consegue:

> **partir de um problema de negócio, levantar requisitos, modelar o domínio, definir regras, projetar uma arquitetura, implementar usando Ports and Adapters, testar as regras independentemente da infraestrutura e medir o comportamento do sistema.**

Ao terminar, você deverá conseguir explicar tecnicamente:

- por que escolheu Arquitetura Hexagonal;
- onde estão as regras de negócio;
- por que determinada classe pertence ao domínio;
- por que determinado código pertence a um adapter;
- como o sistema mantém suas invariantes;
- como o banco participa das transações;
- como o sistema se comporta diante de erros;
- quais são seus gargalos;
- quais seriam os próximos passos para escalar a aplicação.

---

# 37. Regra da trilha

Este projeto é o **Projeto 01**.

Não adicionar tecnologias apenas para "deixar o projeto mais bonito".

A evolução deverá acontecer quando existir uma necessidade arquitetural real.

A progressão esperada é:

```text
Projeto 01
Monólito
+
Hexagonal
+
PostgreSQL
+
Testes
        │
        ▼
Projeto futuro
Kafka
        │
        ▼
Outbox
        │
        ▼
Redis
        │
        ▼
Observabilidade
        │
        ▼
Kubernetes
        │
        ▼
Microsserviços
        │
        ▼
CQRS / Saga
        │
        ▼
Sistemas distribuídos
```

Assim, cada projeto da trilha acrescentará **um novo problema de engenharia**, em vez de simplesmente acumular tecnologias.

