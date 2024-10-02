# Spring Cloud Bus 개요

Spring Cloud Bus는 Configuration의 값이 변경될 때, 각 Microservice를 수동으로 재기동하거나 Actuator의 refresh 기능을 사용하는 번거로움을 줄여주는 기술이다. Spring Cloud Bus는 분산 시스템의 각 노드를 경량 메시지 브로커(RabbitMQ)와 연결하여 상태 및 구성 변경 사항을 자동으로 전달한다. 이를 통해 더 많은 서비스에 대해 구성 변경을 실시간으로 반영할 수 있다.

Spring Cloud Bus는 AMQP(Advanced Message Queuing Protocol)를 통해 각 서비스 간에 변경 정보를 전달한다. 이때 하나의 Microservice에서 값이 변경되면 나머지 Microservice들은 Spring Cloud Bus를 통해 변경된 정보를 받는다. Actuator의 refresh를 호출하지 않아도 변경 사항이 적용된다.

<br><br>

### AMQP (Advanced Message Queuing Protocol)

AMQP는 메시지 지향 미들웨어를 위한 개방형 표준 응용 계층 프로토콜이다. RabbitMQ는 Erlang으로 구현된 AMQP 브로커이다.

- 메시지 지향
- 큐잉
- 라우팅 (P2P, Publisher-Subscriber)
- 신뢰성
- 보안

<br><br>

### Kafka와 RabbitMQ 비교

| RabbitMQ | Kafka |
| --- | --- |
| 메시지 브로커 | Pub/Sub 모델 기반 메시지 시스템 |
| 초당 20개 이상의 메시지 전달 | 초당 100k 이상의 이벤트 처리 |
| 메시지 전달 보장 | 빠른 처리, Ack를 기다리지 않음 |
| 브로커 및 소비자 중심 | 생산자 중심 |
| 적은 데이터 안전하게 전달 | 대용량 데이터 빠르게 처리 |

<br><br>

### Actuator Bus Refresh Endpoint

Spring Cloud Bus를 이용하면 Actuator의 `bus-refresh` endpoint를 통해 쉽게 변경 사항을 모든 서비스에 전달할 수 있다. `POST /actuator/busrefresh` 요청을 통해 Cloud Bus에 변경 사항을 알리고, 모든 연결된 Microservice에 업데이트된 값을 적용할 수 있다. 

이때 어떤 서비스에 요청을 보내느냐는 중요하지 않으며, 모든 Microservice들이 동일하게 변경 사항을 적용받게 된다.

<br><br>

### Kafka 프로젝트 (Messaging System)

Kafka는 Apache Software Foundation에서 Scalar 언어로 개발한 오픈소스 메시지 브로커 프로젝트이다. Kafka는 대용량 데이터를 분산형 스트리밍 플랫폼으로 처리할 수 있는 메시징 시스템으로, Publisher가 Topic에 메시지를 보내면 Subscriber들이 이를 받아 처리한다.
