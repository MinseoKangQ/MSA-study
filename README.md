## 🚀 Spring Cloud로 개발하는 마이크로서비스 애플리케이션(MSA)

### 💡 개발 환경 (공통)
- 운영체제 : MacOS M1
- 언어 : Java
- 타입 : Gradle - Groovy
- JDK : 17
- Java : 17
- SpringBoot : 3.3.3

<br>

### ✏️ discovery-service
- http://localhost:8761
- Dependencies : Spring Cloud Discovery의 Eureka Server

<br>

### ✏️ user-service
- http://localhost:? (Using random port)
- Dependencies
	- Spring Cloud Discovery의 Eureka Discovery Client
 	- Spring Boot DevTools
	- Lombok
	- Spring Web

<br>

### ✏️ first-service, second-service
- first-service : http://localhost:8081
- second-service : http://localhost:8082
- Dependencies
	- Lombok
	- Spring Web
	- Eureka Discovery Client

<br>

### ✏️ apigateway-service
- http://localhost:8000
- Dependencies
  - Lombok
	- DevTools
	- Eureka Discovery Client
	- Spring Cloud Routing의 Gateway
  - netty 관련 의존성 (MacOS M1 오류 해결 목적)
