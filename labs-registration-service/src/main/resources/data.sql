insert into Categories (name, description) values ('전환', 
'### 목표
기존에 운영하고 있던 애플리케이션을 클라우드 애플리케이션으로 전환  
### 대상
* 운영중인 시스템을 클라우드 애플리케이션으로 전환하려는 시스템 개발자
### DT Labs 클라우드 전환 효과를 최대한 보기 위해서 아래 항목은 필수입니다.
* 전환을 수행할 **기존에 운영중인 애플리케이션**(애플리케이션의 이해도가 높은)이 있어야함
* 프로젝트 리더 포함 **개발가능한** 인력으로 4-5명으로 그룹 구성
* 계획된 기간동안 **DT Labs 상주** (SK U타워 24층) 

![Alt text](img/madang.jpg)
### 전환 프로세스
신청 → Scoping → 사전교육 → Incoming → Replatforming → Refactoring → (MSA) → Devops → Outgoing

* **신청** : 질의서 내용 작성해서 사이트로 신청
* **Scoping** : 신청서 접수 후 Scoping 단계를 통해서 전환 일정 계획 , 계획된 기간동안 상주 
(Scoping과정에서 필요시 오프라인 회의가 진행될 수 있습니다.)
* **사전교육** : DT Labs에서 Cloud Application 전환에 필요한 기술에 대한 사전 교육으로 진행됩니다.
* **Incoming**
	* Scoping시 계획한 내용을 바탕으로 상세 일정을 지정된 담당자와 수립합니다.
	* 계획에 따라 Replatforming, Refactoring 단계를 거치면서 Cloud Application으로 전환을 수행합니다.
	* 마이크로서비스가 필요한 애플리케이션의 경우 마이크로서비스까지 전환을 수행합니다.
	* 단계별로 DT Labs의 각 분야 전문가들이 Point Coaching이 진행됩니다.
	* 매일 정해진 시간에 DT Labs의 담당자와 스크럼 미팅을 통해서 이슈를 공유하고 해결합니다.
* **Outgoing**
	* 퇴소후에는 온라인 지원센터를 통해 기술지원을 계속합니다.');
    
    
insert into Categories (name, description) values ('신규', 
'### 목표
클라우드 애플리케이션 신규 구축시 아키텍처 수립 및 클라우드 애플리케이션 개발 고려사항 도출
### 대상
* 클라우드 환경에서 구동되는 애플리케이션을 신규 구축하려는 아키텍트 및 개발자
### 신규 프로세스
신청 → Scoping → (사전교육) → Incoming → 아키텍처수립 → 프로젝트 구성 → Outgoing

* **신청** : 질의서 내용 작성해서 사이트로 신청
* **Scoping** : 신청서 접수 후 Scoping 단계를 통해서 전환 일정 계획 , 계획된 기간동안 상주 
(Scoping과정에서 필요시 오프라인 회의가 진행될 수 있습니다.)
* **사전교육** : 아키텍처 수립 목적이라면 사전교육은 필수 아니지만 아키텍처 수립이후 개발과정까지 진행시 사전교육 수강 필요함
* **Incoming**
	* 클라우드 애플리케이션 구축시 고려해야할 사항등을 습득하여 아키텍처 수립
	* 프로젝트 구성후 클라우드 애플리케이션 고려사항 적용해서 개발
* **Outgoing**
	* 퇴소후에는 온라인 지원센터를 통해 기술지원을 계속합니다.');


insert into Categories (name, description) values ('Cloud 일반 교육', 
'### 목표
클라우드 애플리케이션 전반에 대한 일반 이론 교육
### 대상
* 클라우드 애플리케이션 전환계획 수립을 위해 클라우드 애플리케이션 전반에 대한 이해가 필요한 시스템 운영자 및 개발자
* 실제 개발업무는 아니지만 클라우드 애플리케이션의 전반적인 특징에 대한 이해가 필요한 업무를 수행하는 사람
### 교육 프로세스
신청 → 일정 공유 → 교육 수강

* **신청** : 질의서 내용 작성해서 사이트로 신청
* **일정 공유** : 교육가능한 일정을 공유하고 조율
* **교육 수강** : 두시간정도로 진행되는 교육 수강
	* 교육 커리큘럼
		* PaaS 기본
		* 클라우드 애플리케이션 특징
		* 클라우드 애플리케이션 전환 과정
		* 클라우드 애플리케이션 전환시 고려사항 등');
        
        
        
        
insert into Questions (category, contents, version, reg_date) values 
('1'
, '{"questionnaire":[{"questions":[{"name":"Name","id":"002","type":"text","hasEtc":false,"reply":["홍길동"],"required":true},{"name":"Team","id":"003","type":"text","hasEtc":false,"reply":["Cloud Platform팀"],"required":true},{"name":"신청 희망 기간","id":"004","type":"text","hasEtc":false,"reply":["20170515~20170630"],"required":true},{"name":"신청 희망 인원","id":"005","type":"text","hasEtc":false,"reply":["5"],"required":true}],"step":1,"category":"일반정보"},{"questions":[{"name":"시스템 설명","id":"101","type":"text","hasEtc":false,"reply":["사내에서 사용하는 자산을 등록해서 관리하는 시스템"],"required":true},{"name":"시스템 사용자","id":"102","type":"checkbox","hasEtc":true,"reply":["사내 구성원","사내 특정 팀","그룹사(SKB,SKT 등)","대고객 서비스"],"required":true},{"name":"OS","id":"103","type":"checkbox","hasEtc":true,"reply":["Linux","Windows","OS X"],"required":true},{"name":"Database","id":"104","type":"checkbox","hasEtc":true,"reply":["Oracle","MySQL","MSSQL","MongoDB"],"required":true},{"name":"Middleware(버전 명시 필수)","id":"105","type":"text","hasEtc":false,"reply":["Tomcat7.0 or JBoss 5.0 or WebSphere 등"],"required":true},{"name":"Framework(버전 명시 필수)","id":"106","type":"text","hasEtc":false,"reply":["Spring3.0 or Nexcore J2EE 5.0 or Nexcore CIP 2.0 등"],"required":true},{"name":"개발 언어(버전 명시 필수)","id":"107","type":"text","hasEtc":false,"reply":["Java1.7 or C# or .NET 등"],"required":true},{"name":"시스템 개선점","id":"108","type":"checkbox","hasEtc":true,"reply":["배포 프로세스","테스트 프로세스","시스템 장비 노후화","어플리케이션 노후화","어플리케이션 버전 관리"],"required":true}],"step":2,"category":"시스템 정보"},{"questions":[{"name":"새로운 개발 요구사항 발생 빈도","id":"201","type":"radio","hasEtc":true,"reply":["월 15회 이상","월 5회 이상-15회 미만","월 5회 미만","거의 없음"],"required":true},{"name":"배포 주기","id":"202","type":"radio","hasEtc":true,"reply":["월 5회 이상","월 5회 미만","분기별","요구사항이 있을 때마다 수시로"],"required":true},{"name":"배포 프로세스","id":"203","type":"text","hasEtc":false,"reply":["Jenkins사용 or 배포 tool은 없고 NW운용팀 관제 하에 서버를 셧다운 및 재시동함 등"],"required":true},{"name":"배포 총 소요시간(테스트 포함)","id":"204","type":"text","hasEtc":false,"reply":["12시간"],"required":true},{"name":"장애 발생 빈도","id":"205","type":"radio","hasEtc":false,"reply":["높음","낮음"],"required":true},{"name":"많이 발생하는 장애유형","id":"206","type":"checkbox","hasEtc":true,"reply":["네트워크 장애","프레임워크 장애","배포 후 테스트 부족으로 인한 장애","연동 시스템으로 인한 장애"],"required":true},{"name":"장애 민감성","id":"207","type":"radio","hasEtc":false,"reply":["민감하지 않음","보통","매우 민감"],"required":true},{"name":"시스템 트래픽 변동량","id":"208","type":"radio","hasEtc":false,"reply":["명절, 공휴일 등 트래픽 증가","월초, 월말 등 특정한 시기에 트래픽 증가","예측 불가능","항상 비슷한 트래픽 발생"],"required":true},{"name":"배포 주기","id":"209","type":"radio","hasEtc":true,"reply":["월 5회 이상","월 5회 미만","분기별","요구사항이 있을 때마다 수시로"],"required":true},{"name":"시스템 다중화 구성 여부(서버 규모)","id":"210","type":"text","hasEtc":false,"reply":["Linux 가상화(CPU 2Core, Memory 4GB), WAS 이중화, DB1대 등"],"required":true},{"name":"화면 본수","id":"211","type":"radio","hasEtc":false,"reply":["약 500본 미만","약 500본 이상","약 1000본 이상","약 3000본 이상"],"required":true}],"step":3,"category":"클라우드 전환 필요성 판단"},{"questions":[{"name":"OS, 특정 솔루션(3rd party 라이브러리),하드웨어에 종속적인 코드가 있습니까?","id":"301","type":"text","hasEtc":false,"reply":["Windows OS에서만 사용가능한 보안 솔루션 사용 등"],"required":true},{"name":"연동하는 타 시스템이 있습니까? (있다면 연동하는 시스템과 방식 명시)","id":"302","type":"text","hasEtc":false,"reply":["SAP ERP(JCO), 사내시스템(DBLink연동), SSO, 전자결제시스템(URL연동) 등"],"required":true},{"name":"다른 시스템에서 활용하도록 노출한 API나 데이터가 있습니까?","id":"303","type":"text","hasEtc":false,"reply":["API로 특정 기능 노출하고 있음 or 다른 시스템에서 해당 시스템의 디비에 접속해서 데이터를 수집, 저장, 업데이트함"],"required":true},{"name":"사용하고 있는 서비스가 있습니까? (모두 기입)","id":"304","type":"text","hasEtc":false,"reply":["대덕 데이터센터 Oracle DB, Redis, MongoDB, RabbitMQ 등"],"required":true},{"name":"HTTP 외 다른 통신 프로토콜을 사용합니까?","id":"305","type":"checkbox","hasEtc":true,"reply":["TCP","FTP","SFTP","SMTP"],"required":true},{"name":"J2EE API를 사용합니까?","id":"306","type":"checkbox","hasEtc":true,"reply":["JCA","JTA","JMS"],"required":false},{"name":"형상 관리도구를 사용합니까?","id":"307","type":"checkbox","hasEtc":true,"reply":["SVN","GIT"],"required":false},{"name":"의존성 관리도구를 사용합니까?","id":"308","type":"checkbox","hasEtc":true,"reply":["Maven","Gradle"],"required":false},{"name":"소스코드에 설정 정보들이 포함되어 있습니까? (설정파일을 사용하거나 IP/PORT 등이 소스에 하드코딩되어 있는 경우)","id":"309","type":"radio","hasEtc":false,"reply":["예","아니오"],"required":false},{"name":"파일로그를 사용합니까? (예: log4j, slf4j 를 통한 FileAppender를 사용하는 경우 등)","id":"310","type":"radio","hasEtc":false,"reply":["사용함","사용하지 않음"],"required":false},{"name":"영구적으로 파일을 저장합니까?","id":"311","type":"radio","hasEtc":false,"reply":["저장함","저장하지 않음"],"required":false},{"name":"HTTP 세션을 사용합니까?","id":"312","type":"radio","hasEtc":false,"reply":["사용함","사용하지 않음"],"required":false},{"name":"배치 프로세스가 있습니까? 있다면 어떤 방식으로 구현되어 있습니까?","id":"313","type":"text","hasEtc":false,"reply":["spring scheduler 사용 or quartz 사용/ 5분 간격으로 DB 데이터를 수집하여 Redis에 적재, 매일 AM 5:00 DB에서 일부 데이터를 삭제 등 "],"required":false},{"name":"서비스를 강제로 종료하는 코드가 있습니까? (예: System.exit())","id":"314","type":"radio","hasEtc":false,"reply":["있음","없음"],"required":false},{"name":"새로운 인스턴스를 생성하는 코드가 있습니까? (예: Runtime.exec())","id":"315","type":"radio","hasEtc":false,"reply":["있음","없음"],"required":false},{"name":"공통 유틸성 jar가 있습니까?","id":"316","type":"radio","hasEtc":false,"reply":["있음","없음"],"required":false}],"step":4,"category":"클라우드 전환 가능성 판단"},{"questions":[{"name":"DTLABS 담당자에게 전하고 싶은 말이 있다면 기입해 주십시오.","id":"401","type":"textarea","hasEtc":false,"reply":[""],"required":false}],"step":5,"category":"기타"}],"notice":null}'
,'v1.0.0',curdate());

insert into Questions (category, contents, version, reg_date) values 
('2'
,'{"questionnaire":[{"questions":[{"name":"Name","id":"002","type":"text","hasEtc":false,"reply":["홍길동"],"required":true},{"name":"Team","id":"003","type":"text","hasEtc":false,"reply":["Cloud Platform팀"],"required":true},{"name":"신청 목적","id":"004","type":"text","hasEtc":false,"reply":["마이크로서비스를 고려한 어플리케이션 설계"],"required":true},{"name":"신청 희망 기간","id":"005","type":"text","hasEtc":false,"reply":["20170515~20170630"],"required":true},{"name":"신청 희망 인원","id":"006","type":"text","hasEtc":false,"reply":["5"],"required":true}],"step":1,"category":"일반정보"},{"questions":[{"name":"구축 시스템 설명","id":"101","type":"text","hasEtc":false,"reply":["사내에서 사용하는 자산을 등록해서 관리하는 시스템"],"required":true},{"name":"구축 시스템 사용자","id":"102","type":"checkbox","hasEtc":true,"reply":["사내 구성원","사내 특정 팀","그룹사(SKB,SKT 등)","대고객 서비스"],"required":true},{"name":"필요한 서비스","id":"103","type":"text","hasEtc":false,"reply":["MariaDB, RabbitMQ, MongoDB 등"],"required":true}],"step":2,"category":"시스템 정보"},{"questions":[{"name":"DTLABS 담당자에게 전하고 싶은 말이 있다면 기입해 주십시오.","id":"201","type":"textarea","hasEtc":false,"reply":[""],"required":false}],"step":3,"category":"기타"}],"notice":null}'
,'v1.0.0'
,curdate()
);

insert into Questions (category, contents, version, reg_date) values 
('3'
,'{"questionnaire":[{"questions":[{"name":"Name","id":"002","type":"text","hasEtc":false,"reply":["홍길동"],"required":true},{"name":"Team","id":"003","type":"text","hasEtc":false,"reply":["Cloud Platform팀"],"required":true},{"name":"교육 수강 희망 기간","id":"004","type":"text","hasEtc":false,"reply":["20170515~20170630"],"required":true},{"name":"교육 수강 희망 인원","id":"005","type":"text","hasEtc":false,"reply":["5"],"required":true}],"step":1,"category":"일반정보"},{"questions":[{"name":"DTLABS 담당자에게 전하고 싶은 말이 있다면 기입해 주십시오.","id":"101","type":"textarea","hasEtc":false,"reply":[""],"required":false}],"step":2,"category":"기타"}],"notice":null}'
,'v1.0.0'
,curdate()
);