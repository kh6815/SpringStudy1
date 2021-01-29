# SpringStudy1
Mysql + Chrome - YARC +Spring JPA Study


*** Spring vs SpringBoot
- Spring은 java 엔터프라이즈 개발을 편하게 해주는 오픈소스 프레임워크라고 생각하면된다.
  하지만 옛날에 스프링 프레임워크를 설정하기 위해서는 여러가지 라이브러리, 설정들을 직접 다 설정하면서 진행해야하는 큰 
  어려움이 있었다. -> 또한 이 과정 가운데 버전충돌이라던지 여러가지 에러가 많이 발생했고 많은 사람들이 스프링을 공부하기에 어려움을 가졌다.
  이러한 이유때문에 java는 생산성이 떨어진다는 말도 나왔다.

- Sprigboot는 Spring 개발을 하면서 기본적으로 설정을 해야됬던 라이브러리들을 미리 내장하고 있으며, 또는 간편하게 애노테이션을 통해 설정할 수
  있도록 개발된 경량의 프레임워크이다. 스프링부트에서는 스프링을 기본적으로 포함하고 있으며, 또한 톰캣서버까지 내장하고 있다.


*** Rest API
REST 의 개념- HTTP 프로토콜에 있는 Method를 활용한 아키텍처 스타일이다.
- HTTP Method 를 통해서 Resource를 처리 한다.
- CRUD 를 통한 Resource 조작을 할 때 사용 한다.

- HTTP Method 동작 URL 형태
  GET 조회 ( SELECT * READ) /user/{id}
  POST 생성 ( CREATE ) /user
  PUT / PATCH 수정(UPDATE) *CREATE /user
  DELETE 삭제(DELETE) /user/{1}


*** Lombok 
- Lombok을 사용하여 여러 변수들의 생성자 자동 생성과 get,set 메서드를 손쉽게 사용할 수 있다.


*** JPA(Java Persistent API)
- ORM(Object Relational Mapping)으로, 데이터베이스 정보를 객체지향으로 손쉽게 활용할 수 있도록 도와주는 도구이다.
- 자바 객체(Object)와 관계형 데이터베이스 둘간의 맵핑을 통해서, 쿼리에 집중하기보다 객체에 집중하여 조금 더 프로그래밍적으로 
  많이 활용할 수 있다.


*** Entity
- Camel Case : 단어를 표기할 때 소문자로 시작하여 띄어쓰기 대신 대문자로 단어를 구분 -> 보통 Java 변수를 선언할 때 camel case를 사용
  ex) phoneNumber, updatedAt
- Snack Case : 단어를 표기할 때 모두 소문자로 표기, 띄어쓰기 대신 _로 표기
  ex) phone_number, updated_at
  API를 정의하기에 따라 다르지만, 주로 통신 규격 구간에서는 Snack Case를 많이 사용함. -> DB칼럼데이터, http json형식

- Entity : JPA에서는 테이블을 자동으로 생성해주는 기능 존재. DB Table == JPA Entity
  @Entity = 해당 class가 Entity임을 명시
  @Table         = 실제 DB테이블의 이름을 명시
  @Id            = Index primary key를 명시
  @Column         = 실제 DB Column의 이름을 명시
  @GeneratedValue = Primary key 식별키의 전략 설정 

jpa의 Entity 및 column은 자동으로 camel case -> DB의 snack case에 매칭 시켜줍니다.




*** Repository
 - Repository 애노테이션을 통해 따로 쿼리문을 작성하지 않아도 기본적이 CRUD가 가능하다.
 ex)
@Repository
public interface UserRepository extends JapRepository<User, Long>{}


*** JAP 연관관계 설정
일대일 - @OneToOne
일대다 - @OneToMany
다대일 - @ManyToOne
다대다 - @ManyToMany


*** 여러가지 애노테이션 정리
- @NoArgsConstructor : 기본 생성자 생성 
  @AllArgsConstructor : 클래스안에 있는 모든 변수들을 매개변수로 갖는 생성자 생성
  @Data(@Getter, @Setter로 나눌 수 있음) : 클래스안에 있는 변수들의 getter, setter 생성
  @EntityListeners(AuditingEntityListener.class) 
     - @CreatedDate      : AuditingEntityListener를 통해 처음 생성될때 자동으로 값이 들어가짐.
     - @CreatedBy        : AuditingEntityListener를 통해 처음 생성될때 자동으로 값이 들어가짐.
     - @LastModifiedDate : AuditingEntityListener를 통해 업데이트될때 자동으로 값이 들어가짐.
     - @LastModifiedBy   : AuditingEntityListener를 통해 업데이트될때 자동으로 값이 들어가짐.
-----------------------------------------------------------------------------------------------------------------------------

스프링 부트 미니 프로젝트 (어드민 페이지 만들기)

1. ERD 설계
2. Table 생성
3. Entity 생성
4. Repository 생성
5. Repository 테스트
6. 연관관계 설정
7. 필요한 Query Method 생성



-----------------------------------------------------------------------------------------------------------------------------

1. ERD 설계 -(Entity Relationship Diagram - 개체 관계 모델) 

 - MySql Workbench를 통해 데이터베이스 구축
![캡처](https://user-images.githubusercontent.com/62634760/106245649-27d35880-6250-11eb-8cf5-38b106a743f8.PNG)

 - user엔티티는 order_group(user의 총 주문내역, 장바구니)를 여러개 갖을 수 있으므로 일대다 관계 -> user 1 : N order_group 
 - order_group은 여러개의 order_detail(아이템 주문내역, 장바구니에 담겨있는 아이템 주문내역들)을 가질 수 있으므로 일대다 관계 -> order_group 1 : N order_detail
 - item(한가지 아이템이 여러개의 주문 내역을 가질 수 있음)은 여러개의 order_detail를 가질 수 있으므로 일대다 -> item 1 : N order_detail
 - partner사는 여러개의 item을 사이트에 등록시킬 수 있으므로 일대다 -> partner 1 : N item
 - category 한개의 카테고리에 여러 파트너사가 들어갈 수 있으므로 일대다 -> category 1 : N category

2. Table 생성
![캡처](https://user-images.githubusercontent.com/62634760/106248613-95818380-6254-11eb-8179-e98a5986cec0.PNG)
- DB에 관계설정에 맞게 table 생성

3. Entity 생성
![캡처](https://user-images.githubusercontent.com/62634760/106250791-7506f880-6257-11eb-9c31-0bb08c0a3268.PNG)
 - DB에 설정한 table에 맞게 클래스 Entity 생성하기 

