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


---

## JPA

