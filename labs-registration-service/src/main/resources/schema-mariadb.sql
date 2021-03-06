-- Insert 문에서 한글 관련 인코딩 에러 발생하는 경우, create table ... 수행 전에 database의 character set 을 utf8로 변경해준다
-- ALTER DATABASE yourDatabaseName DEFAULT CHARACTER SET utf8;

CREATE TABLE Categories (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL,
  description VARCHAR(2000) NULL,
  PRIMARY KEY (id));


CREATE TABLE Questions (
  id INT NOT NULL AUTO_INCREMENT,
  category VARCHAR(45) NULL,
  contents LONGTEXT NULL,
  version VARCHAR(45) NULL,
  reg_date DATE NULL,
  PRIMARY KEY (id));


CREATE TABLE Registrations (
  id INT NOT NULL AUTO_INCREMENT,
  question_id INT NOT NULL,
  email VARCHAR(45) NULL,
  contents LONGTEXT NULL,
  reg_date DATE NULL,
  PRIMARY KEY (id),
  CONSTRAINT question_id_FK FOREIGN KEY (question_id)
  REFERENCES Questions(id)); 

create table users(
	username varchar(50) not null primary key,
	password varchar(50) not null,
	enabled boolean not null
);

create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);