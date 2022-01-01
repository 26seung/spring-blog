# spring-blog

## 환경설정

#### 데이터 베이스
Docker 를 이용하여 데이터베이스 mysql 구성하기
```
docker run --platform linux/amd64 -p 3306:3306 --name blog -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=blogDB -e MYSQL_USER=seung -e MYSQL_PASSWORD=1234 -d mysql
```
#### yml 설정

yml 을 사용하는 이유는 무었일까?

데이터를 표현하는 형식이다.
시스템간에 데이터를 주고 받을때 데이터 포맷에 대한 약속이 필요하며 규칙을 세워 운영하는 방식이다.

오래전부터 사용되어 오던 방법은 `Xml` 형식으로 Key 와 Value 로 구분하여 태그를 넣어 부모, 자식구조를 나타낸다.
단점은 코드가 길어져 가독성이 어려움  
반면에 요즘 사용되어지는 `JSON` 방식은 간결하고 가독성이 좋아진다.

스프링부트는 JSP를 지원하지 않는다. 보통 머스태치, 타임리프 등을 이용하며 jsp 를 사용하기 위해 관련 라이브러리를 설치해 이용해야한다,

#### View 설정

스프링에서는 jsp 파일보다는 타임리프, 머스태치 등의 라이브러리를 지원한다고 한다. 그럼에도 jsp를 사용하기 위해서는 필요한 라이브러리를 설치해야만 한.

##### jsp 파일 사용
spring-boot-starter-web 에 포함된 tomcat 은 JSP 엔진을 포함하고 있지 않다. 
jsp 파일은 Springboot 의 templates 폴더안에서 작동하지 않는다. 
그래서 jsp를 적용하기 위해서는 아래와 같은 의존성을 추가해야한다.
            
    implementation 'javax.servlet:jstl'
    implementation "org.apache.tomcat.embed:tomcat-embed-jasper"

#### 스프링 security 태그 라이브러리

    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>

를 view 파일에 추가하여 사용한다. 사용시 `권한처리`를 일일히 if문 분기처리를 사용하지 않고도 간단히 처리해줄수 있다.


---

## JPA

#### ORM (Object Relational Mapping)

ORM 은 객체와 관계 매핑의 줄임말 이다. 
우리가 OOP(Object Oriented Programming)에서 쓰이는 객체라는 개념을 구현한 클래스와 RDB(Relational DataBase)에서 쓰이는 데이터인 테이블을 자동으로 매핑(연결) 하는 것을 의미한다. 
그러나, 클래스와 테이블은 서로가 기존부터 호환성을 두고 만들어진 것이 아니기 때문에 불일치가 발생하는데, 
이를 ORM 을 통해 객체간의 관계를 바탕으로 SQL문을 자동으로 생성하여 불일치를 해소한다. 
따라서 ORM 을 이용하면 SQL 문을 짤 필요 없이 객체를 간접적으로 데이터베이스를 조작할 수 있다.

- 장점
  - 객체 지향적인 코드
  - 재사용, 유지보수, 리팩토링 용이성
  - DBMS 에 대한 종속성이 줄어든다


#### 영속화(Persistence)

- 데이터를 생성한 프로그램이 종료되더라도 사라지지 않는 데이터의 특성을 이야기한다.
- 영속성을 가지고 있지 않은 데이터는 단지 `메모리` 에서만 존재하기 때문에 프로그램이 종료되면 사라진다.
- ORM 을 사용하여 조회한 엔티티도 영속성 컨텍스트가 관리하는 `영속 상태` 이다

#### 영속성 컨텍스트 (Persistence Context)

- 영속성 컨텍스트란 엔티티 (Entity)를 `영구 저장` 하는 환경을 말한다.
- 엔티티 매니저 (Session)을 생성할 때 만들어진다. 그리고 이를 통해 영속성 컨텍스트에 접근이 가능하며 관리를 할 수 있다.
- 여러 엔티티 매니저 (Session)이 같은 영속성 컨텍스트에 접근할 수 있다. (N:1)
- 영속성 컨텍스트 (엔티티매니저)에는 내부에 `1차캐시` 가 존재한다.
  - 조회 를 하는순간 1차캐시를 먼저 찾는다
  - 1차캐시에 엔티티가 존재하면 바로 반환하며 DB를 조회하지 않는다.
  - 글로벌하지 않다. 해당 스레드가 종료되면 함께 사라진다. (글로벌하게 사용하는 것은 "2차캐시" 라고 한다.)
  

#### 영석성 컨텍스트와 데이터베이스 저장
- JPA 는 트랜잭션을 커밋하는 순간 영속성 컨텍스트에 새로 저장된 엔티티를 데이터베이스에 저장한. 이를 Flush 라고 한다.
- 플러시는 영속성컨텍스트의 내용을 데이터베이스에 `동기화` 시키는 작업인데 이때 등록,수정,삭제한 엔티티를 데이터베이스에 반영한다.


---

### Validation

#### @NotNull, @NotEmpty, @NotBlank 

- 우선 `@NotNull` 은 위에 살펴본 것 처럼 이름 그대로 Null 값만 허용하지 않는다. "" 이나 " " 은 허용하게 된다.



---

#### json 데이터 통신
스프링 컨트롤러는 `key = value` 형식으로 데이터를 파싱하여 변수에 담아준다.
  setter 을 사용하여 `key = value` 데이터를 스프링에 파싱하여 넣어준다.
Get 요청은 key=value 형태
Post,Put,Delete 요청은 Body에 데이터를 담아 보내므로 json 형태로 통일하는 것이 좋다.
`key = value` 아닌 데이터는 @RequestBody 어노테이션을 활용하면 MessageConverter 클래스를 구현한 Jackson 라이브러리가 발동하여 json 형태의 데이터를 자바 오브젝트로 파싱하여 받아준다.



---


정리 

    javax.persistence.Id: 관계형 DB에서 사용. 
    import org.springframework.data.annotation.Id: JPA에 의해 지원되지 않는 Nosql이나 프레임워크에서 사용됨.


#### ajax 를 사용하는 이유
1. 비동기 통신 방법이기 때문
2. 서버로부터 응답을 받을 때 JSON 데이터를 받기 위해서
   1. 웹은 HTML파일을 받고 앱은 데이터 (JSON)을 받는다.

---
#### 트랜잭션

트랜잭션은 데이터베이스의 상태를 변경시키는 하나의 논리적 기능을 수행하기 위한 작업의 단위 또는 한꺼번에 모두 수행되어야 하는 일련의 연산을 말한다.

##### 트랜잭션의 성질
1. 원자성 
2. 일관성
3. 독립성
4. 영속성

---

#### View 설정

