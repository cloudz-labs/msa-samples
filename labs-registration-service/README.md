# labs-registration-service

DT Labs 포털(`https://labs.skcc.com/`)에서 상단의 Register 메뉴에 대한 Backend API 서비스입니다.

DT Labs에 대한 정보와 사용자가 원하는 과정에 등록하면 담당자에게 신청 알림을 전송하는 등의 기능을 제공합니다.

### System Requirements

- Java 8
- Git
- Maven 3.0+
- CloudFoundry CLI


### Spring Framework

| Name             | Version         |
| ---------------- | --------------- |
| Spring Framework | 4.3.8.RELEASE   |
| Spring Boot      | 1.5.3.RELEASE   |
| Spring Cloud     | Dalston.RELEASE |


### Applied Services

- Eureka Client
- Hystrix
- Swagger

### Required Services

| 서비스 종류                    | 서비스 이름                   |
| --------------------------- | --------------------------- |
| Discovery Service           | labs-discovery-service    |
| Redis                       | labs-redis                | 
| Maria DB                    | mariadb-labs              |


## Running locally

> 로컬에서 정상적으로 구동되기 위해서 labs-discovery-service 앱이 구동되어 있어야 합니다.

로컬 환경에서는 H2 Database를 사용합니다.
 `src/main/resources/` 경로에 위치한 `schema-h2.sql`, `data.sql` 스크립트를 실행합니다.

로컬 시스템에서 앱을 구동하기 위해 커멘드라인에서 다음의 명령어를 실행합니다.
```sh
mvn spring-boot:run
```

앱이 정상적으로 구동되면 discovery server의 대시보드 페이지(`http://localhost:8761/eureka`)를 열어 `labs-registration-service` 이름의 앱이 추가되었는지 확인합니다.


## Building

커멘드라인에서 다음의 명령어를 실행합니다.
```sh
mvn clean install -DskipTests=true
```

## Deploying

앱을 PaaS 시스템에 배포하기 위해 커멘드라인에서 다음의 명령어를 실행합니다.
```sh
cf push
```

앱이 성공적으로 배포되면 PaaS 상에 구동되어 있는 discovery server의 대시보드 페이지(<labs-discovery-service 앱의 URL>/eureka)를 열어 `labs-registration-service` 이름의 앱이 추가되었는지 확인합니다.

## Documentation

 이 서비스는 Swagger와 연동 되어 있어 `(http://{ip}:{port}/swagger-ui.html)` url을 통해 API 명세 확인이 가능합니다.
 

