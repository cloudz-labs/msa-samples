# labs-user-bff-service
Spring Boot + React 기반의 BFF(Backend For Frontend) 프로젝트입니다.

## 적용된 서비스
- Zuul

## 적용된 라이브러리 버전
- Java 1.8
- Spring Boot 1.5.3.RELEASE (Spring 4.3.8.RELEASE)
- Spring Cloud Dalston.RELEASE
- frontend-maven-plugin 1.4

## 연동 서비스
- labs-apigateway-service

## 프로젝트 구조
`src/main/app` 내에 [create-react-app](https://github.com/facebookincubator/create-react-app) 기반으로 구성된 frontend 프로젝트가 구성되어 있습니다. `pom.xml` 에서는 [frontend-maven-plugin](https://github.com/eirslett/frontend-maven-plugin) 을 이용하여 react 애플리케이션을 빌드한 후 `target/classes/static` 으로 빌드 결과물을 복사하도록 설정하고 있습니다.

### 로컬 개발 구동
Spring BFF를 가동하여 라우팅을 활성화 시키고, 사용자의 수정에 따른 UI의 즉시 반영을 위하여 `create-react-app`의 개발서버 기능을 구동합니다.
```
$ mvn spring-boot:run
$ cd src/main/app
src/main/app $ npm run start
```
이후 http://localhost:3000 으로 접속하여 개발을 진행합니다.