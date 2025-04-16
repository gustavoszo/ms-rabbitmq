
# **Microserviços - Serviço de mensageria com RabbitMQ**

Este projeto implementa uma arquitetura de microserviços desenvolvidos com Java e Spring para gerenciar pedidos e pagamentos. O sistema realiza a comunicação assíncrona entre os microserviços de **Pedidos** e **Pagamentos** usando o Message Broker - **RabbitMQ**.

## **Visão Geral dos Microserviços**

O sistema é composto por três principais microserviços:

1. **Serviço de Pagamentos**: Responsável por gerenciar o processamento de pagamentos e a comunicação com o serviço de pedidos quando um pagamento é aprovado.
2. **Serviço de Pedidos**: Responsável pelo gerenciamento de pedidos e interação com o serviço de pagamentos para registrar a aprovação dos pagamentos.
2. **Serviço de Avaliação**: Consumidor de mensagens para seguir com o processo de avaliação.


## **Arquitetura**

A arquitetura do sistema utiliza **RabbitMQ**, o servidor **RabbitMQ** é executado em um contâiner **Docker** para realizar a comunicação entre os microserviços a partir de um Message Broker

- Microserviços - **Pedidos**, **Pagamentos** e **Avaliação**: Comunicam entre si com o uso de producers, consumers, queues e exchanges do RabbitMQ
- **Eureka Server**: Usado para registrar e localizar microserviços.
- **Gateway**: Usado para os serviços possuirem um ponto de entrada único


## **Tecnologias Usadas**

- **Java** e **Spring Boot**
- **RabbitMQ**: Message Broker utilizado para a comunicação assíncrona entre os microserviços.
- **Docker**: Usado para criar contêineres para o RabbitMQ.
- **Spring Cloud**
- **Banco de dados**

## Como Executar o RabbitMQ com Docker Compose

Execute o Docker Compose para subir o serviço:

```bash
cd seu-repositorio
cd rabbit
docker-compose up --build
