# labs-apigateway-service

DT Labs 시스템을 구성하는 백엔드 서비스와 프론트엔드 서비스의 Edge Server 기능을 담당하는 서비스입니다. 

labs-apigateway-service 앱은 요청된 URL을 백엔드 서비스로 프록싱하기 위해 Spring Cloud Netflix의 Zuul을 사용합니다.

Zuul이 프록싱하는 URL 패턴과 그에 매칭되는 백엔드 서비스의 정보는 다음과 같습니다. 

| URL 패턴                                                  | 백엔드 서비스 이름                                       |
| --------------------------- | --------------------------- |
| /registration-service/**    | labs-registration-service |
| /data-mgmt-service/**       | labs-data-mgmt-service    |


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

- Zuul
- Eureka Client


### Required Services

| 서비스 종류                                                 | 서비스 이름                                                 |
| --------------------------- | --------------------------- |
| Discovery Service           | labs-discovery-service    |


## Running locally

> 로컬 시스템에서 정상적으로 구동하려면 labs-discovery-service 앱이 구동되어 있어야 합니다.

로컬 시스템에서 앱을 구동하기 위해 커멘드라인에서 다음의 명령어를 실행합니다.
```sh
mvn spring-boot:run
```

앱이 정상적으로 구동되면 discovery server의 대시보드 페이지(`http://localhost:8761/eureka`)를 열어 `labs-apigateway-service` 이름의 앱이 추가되었는지 확인합니다.


## Building

커멘드라인에서 다음의 명령어를 실행합니다.
```sh
mvn clean install -DskipTests=true
```

## Deploying

> labs-apigateway-service 앱은 `labs-discovery-service` 이름의 서비스를 사용합니다. 만약  `labs-discovery-service` 서비스가 존재하지 않다면 서비스를 먼저 생성해야 합니다. `labs-discovery-service` 서비스 생성은 labs-discovery-service 앱의 README를 참고합니다.

앱을 PaaS 시스템에 배포하기 위해 커멘드라인에서 다음의 명령어를 실행합니다.
```sh
cf push
```

앱이 성공적으로 배포되면 PaaS 상에 구동되어 있는 discovery server의 대시보드 페이지(<labs-discovery-service 앱의 URL>/eureka)를 열어 `labs-apigateway-service` 이름의 앱이 추가되었는지 확인합니다.

## 서비스화

API Gateway 서비스를 사용하는 앱(labs-user-bff-service, labs-admin-bff-service)을 위해 labs-apigateway-service 앱의 URL을 user-provided-service로 제공합니다.

user-provided-service로 생성하기 위해 커멘드라인에서 다음의 명령어를 실행합니다.
```sh
//Linux/Mac
cf cups labs-apigateway-service -p '{"uri":"labs-apigateway-service 앱의 URL"}'

//Window
cf cups labs-apigateway-service -p "{\"uri\":\"labs-apigateway-service 앱의 URL\"}"
```

