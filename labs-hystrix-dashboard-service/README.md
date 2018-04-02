# labs-hystrix-dashboard-service

DT Labs 시스템을 구성하는 앱의 서킷 브레이커 대시보드 기능을 담당하는 서비스입니다. 


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

- Hystrix Dashboard


## Running locally

로컬 시스템에서 앱을 구동하기 위해 커멘드라인에서 다음의 명령어를 실행합니다.
```sh
mvn spring-boot:run
```

앱이 정상적으로 구동되면 hystrix dashboard 페이지(`http://localhost:6161/hystrix`)를 열어 화면이 정상적으로 출력되는지 확인합니다.


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

앱이 성공적으로 배포되면 hystrix dashboard 페이지(<labs-hystrix-dashboard-service 앱의 URL>/hystrix)를 열어 화면이 정상적으로 출력되는지 확인합니다.

