# dtlabs-data-mgmt-service

DT Labs 포털(`https://dtlabs.skcc.com/`)에서 상단의 DT Library 메뉴에 대한 Backend API 서비스입니다.

카테고리별 자료 목록 조회, 자료 등록/수정/삭제, 자료 다운로드, 자료 검색 등의 기능을 제공합니다.

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

| 서비스 종류                                                 | 서비스 이름                                                 |
| --------------------------- | --------------------------- |
| Discovery Service           | dtlabs-discovery-service    |
| Mongo DB                    | dtlabs-mongo    |
| Object Storage              | dtlabs-object-storage    |


## Running locally

> 로컬에서 정상적으로 구동되기 위해서 dtlabs-discovery-service 앱이 구동되어 있어야 합니다.

Mongo DB를 설치하고 필요한 경우 `src/main/resources/` 경로에 위치한 `application-default.yml` 설정 파일 내용을 변경합니다.
```yml
spring:
  data:
    mongodb:
      host: localhost
      port: 27017
      username: 
      password: 
      database: 
```

로컬 시스템에서 앱을 구동하기 위해 커멘드라인에서 다음의 명령어를 실행합니다.
```sh
mvn spring-boot:run
```

앱이 정상적으로 구동되면 discovery server의 대시보드 페이지(`http://localhost:8761/eureka`)를 열어 `dtlabs-data-mgmt-service` 이름의 앱이 추가되었는지 확인합니다.


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

앱이 성공적으로 배포되면 PaaS 상에 구동되어 있는 discovery server의 대시보드 페이지(<dtlabs-discovery-service 앱의 URL>/eureka)를 열어 `dtlabs-data-mgmt-service` 이름의 앱이 추가되었는지 확인합니다.

## Documentation

 이 서비스는 Swagger와 연동 되어 있어 `(http://{ip}:{port}/swagger-ui.html)` url을 통해 API 명세 확인이 가능합니다.
 
