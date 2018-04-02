# labs-discovery-service

DT Labs 시스템을 구성하는 백엔드 서비스의 Discovery Server 기능을 담당하는 서비스입니다. 


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

- Eureka Server


## Running locally

로컬 시스템에서 앱을 구동하기 위해 커멘드라인에서 다음의 명령어를 실행합니다.
```sh
mvn spring-boot:run
```

앱이 정상적으로 구동되면 discovery server의 대시보드 페이지(`http://localhost:8761`)를 열어 화면이 정상적으로 출력되는지 확인합니다.


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

앱이 성공적으로 배포되면 discovery server의 대시보드 페이지(<labs-discovery-service 앱의 URL>)를 열어 화면이 정상적으로 출력되는지 확인합니다.

## 서비스화

Discovery Server를 사용하는 클라이언트 앱을 위해 labs-discovery-service 앱의 URL을 user-provided-service로 제공합니다.

user-provided-service로 생성하기 위해 커멘드라인에서 다음의 명령어를 실행합니다.
```sh
//Linux/Mac
cf cups labs-discovery-service -p '{"uri":"labs-discovery-service 앱의 URL"}'

//Window
cf cups labs-discovery-service -p "{\"uri\":\"labs-discovery-service 앱의 URL\"}"
```

