# labs-turbine-service

DT Labs 시스템을 구성하는 앱의 circuit breaker aggregator 기능을 담당하는 서비스입니다. 


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

- Turbine
- Eureka Client


### Required Services

| 서비스 종류                                                 | 서비스 이름                                                 |
| --------------------------- | --------------------------- |
| Discovery Service           | labs-discovery-service    |


## Running locally

> 로컬 시스템에서 정상적으로 구동하려면 labs-discovery-service 앱과 labs-hystrix-dashboard-service 앱이 구동되어 있어야 합니다.

로컬 시스템에서 앱을 구동하기 위해 커멘드라인에서 다음의 명령어를 실행합니다.
```sh
mvn spring-boot:run
```

앱이 정상적으로 구동되면 hystrix dashboard 페이지(`http://localhost:6161/hystrix`)를 열고 URL 입력란에 `http://localhost:8989/turbine.stream`을 입력 후 모니터링 화면이 정상적으로 출력되는지 확인합니다.


## Building

커멘드라인에서 다음의 명령어를 실행합니다.
```sh
mvn clean install -DskipTests=true
```

## Deploying

> labs-turbine-service 앱은 `labs-discovery-service` 이름의 서비스를 사용합니다. 만약  `labs-discovery-service` 서비스가 존재하지 않다면 서비스를 먼저 생성해야 합니다. `labs-discovery-service` 서비스 생성은 labs-discovery-service 앱의 README를 참고합니다.

앱을 PaaS 시스템에 배포하기 위해 커멘드라인에서 다음의 명령어를 실행합니다.
```sh
cf push
```

앱이 성공적으로 배포되면 hystrix dashboard 페이지(<labs-hystrix-dashboard-service 앱의 URL>/hystrix)를 열고 URL 입력란에 `<labs-turbine-service 앱의 URL>/turbine.stream`을 입력 후 모니터링 화면이 정상적으로 출력되는지 확인합니다.

