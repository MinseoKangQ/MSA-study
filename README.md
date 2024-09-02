## 🚀 Spring Cloud로 개발하는 마이크로서비스 애플리케이션(MSA)

### ✨ 요약
<table>
  <tr>
    <th>구성 요소</th>
    <th>역할</th>
    <th>설명</th>
  </tr>
  <tr>
    <td><b>📁 Git Repository</b></td>
    <td>소스 관리 및 Profile 관리</td>
    <td>모든 소스 코드와 설정 파일을 Git 저장소에서 관리한다. 환경별 설정(Profile) 정보 또한 저장소에서 버전 관리되며, Config Server에서 이를 참조한다.</td>
  </tr>
  <tr>
    <td><b>🔧 Config Server</b></td>
    <td>설정 관리 및 프로파일 정보 제공</td>
    <td>Git 저장소에 등록된 프로파일 정보 및 설정 정보를 중앙에서 관리하고 제공한다. 각 마이크로서비스는 이 서버로부터 필요한 설정 정보를 가져와서 사용한다.</td>
  </tr>
  <tr>
    <td><b>🌍 Eureka Server</b></td>
    <td>마이크로서비스 등록 및 검색</td>
    <td>각 마이크로서비스가 자신을 등록하고, 다른 서비스의 위치를 검색할 수 있는 레지스트리 역할을 한다. 이를 통해 서비스 간의 동적 확장과 통신이 가능해진다.</td>
  </tr>
  <tr>
    <td><b>🚪 API Gateway Server</b></td>
    <td>부하 분산 및 서비스 라우팅</td>
    <td>클라이언트 요청을 적절한 마이크로서비스로 라우팅하며, 부하 분산, 인증, 속도 제한 등의 기능을 제공한다. 모든 외부 요청은 API Gateway를 통해 처리된다.</td>
  </tr>
  <tr>
    <td><b>🛠 Microservices</b></td>
    <td>사용자, 주문, 카탈로그 서비스 관리</td>
    <td>
      <b>USER-SERVICE</b>: 사용자 관리와 관련된 기능을 담당하는 마이크로서비스<br>
      <b>ORDER-SERVICE</b>: 주문 관리와 관련된 기능을 담당하는 마이크로서비스<br>
      <b>CATALOG-SERVICE</b>: 상품 카탈로그와 관련된 기능을 담당하는 마이크로서비스
    </td>
  </tr>
  <tr>
    <td><b>📨 Queuing System</b></td>
    <td>마이크로서비스 간 메시지 발생 및 구독 관리</td>
    <td>마이크로서비스 간의 메시지 전달을 관리한다. 서비스 간에 비동기적으로 메시지를 주고받으며, 이로 인해 서비스의 독립성이 보장되고 확장성이 향상된다.</td>
  </tr>
</table>


<br>

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
