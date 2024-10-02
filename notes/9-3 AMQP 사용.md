# AMQP 사용
### 1. Dependency 추가

**Config Server**
```
implementation 'org.springframework.cloud:spring-cloud-starter-bootstrap'
implementation 'org.springframework.boot:spring-boot-starter-actuator'
implementation 'org.springframework.cloud:spring-cloud-starter-bus-amqp'
```

<br>

**Users Microservice**, **Gateway Service**
```
implementation 'org.springframework.cloud:spring-cloud-starter-bus-amqp'
```

<br><br>

## 2. Config Server, Users Microservice, Gateway Service에 application.yml 수정

RabbitMQ와 각 서비스 간의 통신을 설정하고 Actuator의 엔드포인트를 노출하여 서비스 간의 구성 변경 사항을 자동으로 전달할 수 있다.

```yaml
# RabbitMQ와 노드를 연결하는 설정
# 웹 브라우저에서는 15672 포트를 사용하지만
# 시스템에서는 5672 포트를 사용

spring:
  rabbitmq:
    host: 127.0.0.1
    port: 5672
    username: guest
    password: guest

management:
  endpoints:
    web:
      exposure:
        # busrefresh 추가로 모든 서비스에 변경 사항을 반영
        include: refresh, health, beans, httptrace, busrefresh

```

<br><br>

## 3. 서비스 실행

1. RabbitMQ Server (`/opt/homebrew/opt/rabbitmq/sbin/rabbitmq-server`)

2. Spring Cloud Config Service

3. Eureka Discovery Service

4. Spring Cloud Gateway Service

5. Users Microservice

<br><br>

## 4. 확인

1. `POST http://localhost:8000/actuator/busrefresh` 요청 보내기
   - 이 요청은 Spring Cloud Bus를 통해 변경 사항을 모든 마이크로서비스에 알린다.
   - 정상적으로 요청이 완료되면, **204 No Content** 응답이 반환된다.

2. 변경된 설정이 자동으로 각 마이크로서비스에 적용되었는지 확인한다.
   - 각 서비스가 새로운 설정으로 재시작되거나 설정이 업데이트된 것을 확인할 수 있다.

