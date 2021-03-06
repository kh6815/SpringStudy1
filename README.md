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
- @AllArgsConstructor : 클래스안에 있는 모든 변수들을 매개변수로 갖는 생성자 생성
- @Data(@Getter, @Setter로 나눌 수 있음) : 클래스안에 있는 변수들의 getter, setter 생성
- @EntityListeners(AuditingEntityListener.class) 
     - @CreatedDate      : AuditingEntityListener를 통해 처음 생성될때 자동으로 값이 들어가짐.
     - @CreatedBy        : AuditingEntityListener를 통해 처음 생성될때 자동으로 값이 들어가짐.
     - @LastModifiedDate : AuditingEntityListener를 통해 업데이트될때 자동으로 값이 들어가짐.
     - @LastModifiedBy   : AuditingEntityListener를 통해 업데이트될때 자동으로 값이 들어가짐.
- @Builder : 요즘은 Builder를 통해 다른 곳에서 해당클래스의 생성자 생성을 쉽게 하기 위해 사용된다.
- @Accessors(chain = true) : @Accessors(chain = true)를 통해서 업데이트시 에 builder와 같이 .으로 값을 바꿔줄 수 있다.
- @RestController : controller 클래스라고 설정
- @RequestMapping("/api/user") : HTTP주소 맵핑, controller클래스에서 사용
- @Slf4j  : 실제 현업에서는 System.out.println();으로 데이터를 보는것이 아니라 로깅시스템을 통해서 로그를 남기기 때문에
            lombok에 있는 Simple Logging 파사드? 4 java인 @Slf4j를 사용하여 로그를 남긴다.
- @RequiredArgsConstructor : lombok사용, Autowired의 애노테이션 없이 final이 붙은 변수에 자동적으로 의존관계주입을 해줌 


*** application.yaml
 - 요즘은 yaml파일을 선호 
![캡처3](https://user-images.githubusercontent.com/62634760/106262373-43495e00-6266-11eb-81f1-ca6fe914d625.PNG)
-----------------------------------------------------------------------------------------------------------------------------

스프링 부트 미니 프로젝트 (어드민 페이지 만들기)

1. ERD 설계
2. Table 생성
3. Entity 생성
4. Repository 생성
5. Repository 테스트
6. 연관관계 설정
7. JPA의 추가기능
8. 필요한 Query Method 생성 및 서비스 구현




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

4. Repository 생성
![캡처](https://user-images.githubusercontent.com/62634760/106251381-3de51700-6258-11eb-9049-251cf43ba064.PNG)
- Repository를 interface로 만들고 JapRepository인터페이스를 상속받아서 <DB에 해당하는 클래스 엔티티, primary key 타입 명시>으로 생성

5. Repository 테스트
![캡처](https://user-images.githubusercontent.com/62634760/106252853-13945900-625a-11eb-86c3-632cf4971c8e.PNG)
- Repository는 @Repository를 통해 @component로 스프링 컨테이너에 등록되기 때문에 Autowired로 자동주입이 가능하다.
- 값을 세팅하고 save를 통해 DB에 값을 저장한다.

6. 연관관계 설정

![캡처](https://user-images.githubusercontent.com/62634760/106255793-d29e4380-625d-11eb-83eb-ca82e9726ab2.PNG)
- @OneToMany(fetch = FetchType.LAZY, mappedBy = "user") -> fetch = FetchType.LAZY는 지연로딩으로 해당하는 값만 먼저 가져온다. 실무에서는 보통 지연로딩을 사용, mappedBy = "user"는 관계설정된 orderGroup클래스의 user 변수와 맵핑하겠다는 의미이다.
- user클래스 : user클래스는 orderGroup과  1 : N관계이므로, user클래스기준 @OneToMany이 붙는다.

![캡처1](https://user-images.githubusercontent.com/62634760/106255801-d4680700-625d-11eb-90a1-43501bd39e1f.PNG)
- orderGroup클래스 : orderGroup클래스는 user와 N : 1관계이므로, orderGroup클래스 기준 @ManyToOne이 붙는다.
- 관계설정된 user클래스의 객체를 변수타입으로 지정한다. -> DB에 저장된 user_id와 맵핑된다.

7. JPA의 추가기능 
- 객체가 생성될 때와 업데이트될 때 자동으로 값을 넣어줄 수 있는 JPA기능
  1) Config 폴더 -> JpaConfig클래스 생성
       : Configuration 애노테이션을 통해 설정파일이라고 설정, EnableJpaAuditing 애노테이션을 통해 JPA에 감시 활성화

     ![캡처](https://user-images.githubusercontent.com/62634760/106260070-5ad31780-6263-11eb-8651-3fabf4e4a71e.PNG)

  2) component폴더 -> LoginUserAuditorAware파일 생성 
       : @Component등록, AuditorAware<String>타입의 인터페이스를 상속한다.
       : Optional<String> getCurrentAuditor()를 오버라이드 해서 ("AdminServer")를 리턴함.

     ![캡처1](https://user-images.githubusercontent.com/62634760/106260081-5e669e80-6263-11eb-8e57-1cdbe7330c7e.PNG)

  3) 사용하고자하는 엔티티 클래스에 @EntityListeners(AuditingEntityListener.class) 설정
       : CreatedBy(엔티티 생성시), LastModifieBy(엔티티 업데이트시) 자동으로 ("AdminServer")값이 들어감
       : @CreatedDate,  @LastModifiedDate 자동으로 시간이 저장됨.

     ![캡처2](https://user-images.githubusercontent.com/62634760/106260089-60306200-6263-11eb-8e32-0dc78568552c.PNG)
     ![캡처3](https://user-images.githubusercontent.com/62634760/106260102-61fa2580-6263-11eb-9776-111b3d6fe7ed.PNG)

8. 필요한 Query Method 생성 및 서비스 구현
- 서비스 구현
  1) HTTP 웹페이지와 json형식으로 데이터를 주고 받기 위해 request, response 형식 만들기
    - 공용데이터로 사용할 Header 부문 작성
    - java에서 변수를 작성할 때는 보통 camel case로 작성하는데 통신규격 구간에서는 snake case를 사용함으로 aplication.yaml파일의 spring :  부분에 'jackson:property-naming-strategy:         SNAKE_CASE'을 추가하여 spring에서 http로 데이터를 전송할 때는 변수 이름을 snake case로 바꿔서 전송
    - Header 클래스를 Header<T> 제네릭 타입으로 만들어, 들어오는 T 타입(Request, Response)의 data 변수를 만들고, static으로 Header의 클래스 변수를 생성한다.
    - 만들어진 메서드의 반환타입을 <T>타입으로 설정하고 Header.build를 통해 공통데이터와 데이터를 만들어준다, retrun 할때 Header를 Header<T>으로 cast해서 return한다.(메서드의 반환타       입을 들어온 타입으로 이미 설정해놨기 때문에 리턴시 타입을 맞춰줘야함.)

      ![캡처](https://user-images.githubusercontent.com/62634760/106265198-067f6600-626a-11eb-9701-34c30d9b5998.PNG)
    - Header에 T타입으로 들어갈 Request와 Response 작성 
     DB에 자동적으로 들어갈 createdAt, createdBy, updatedAt, updatedBy를 제외하고 나머지 칼럼 작성 
    - orderGroup의 Request, Response경우 엔티티의 User user를 userid 칼럼으로 받음

      ![캡처](https://user-images.githubusercontent.com/62634760/106266044-4561eb80-626b-11eb-9f71-c18df377485b.PNG)
      ![캡처1](https://user-images.githubusercontent.com/62634760/106266047-472baf00-626b-11eb-9d8d-55db2134b8a4.PNG)
      ![캡처](https://user-images.githubusercontent.com/62634760/106266379-b1dcea80-626b-11eb-8e39-7fccbdcaef3e.PNG)

   2) CRUD interface 작성(controller와 service 로직에 사용)
    - <Req, Res>을 받아 사용하는 인터페이스를 만듬
     
      ![캡처](https://user-images.githubusercontent.com/62634760/106267154-9a523180-626c-11eb-903a-3893c77ed23c.PNG)

   3) controller : 객체를 return하면 json형식으로 변환하여 보냄
    - controller폴더에 CrudController 추상클래스 작성
    - @Autowired를 사용하기 위해 @Component로 스프링컨테이너에 등록   
    - 추상클래스인 CrudController는 <Req, Res, Entity>를 필요로 함.
    - CrudInterface<Req,Res>를 상속받음.
    - 추상클래스인 BaseService에 의존관계주입
    - Header<Res>를 반환하는 해당 컨트롤러로직 생성
        - create : PostMapping해주고, @RequestBody로 json데이터를 매개변수로 받음
        - read   : GetMapping("{id}") 방식으로 id값을 맵핑, @PathVariable로 id값을 매개변수로 받음
        - update : PutMapping("") 방식을 통해 원하는 업데이트 json값을 가져옴, @RequestBody로 json데이터를 매개변수로 받음
        - delete : DeleteMapping("{id}") 방식으로 id값을 맵핑, @PathVariable로 id값을 매개변수로 받음
    - 해당하는 서비스로직에 넘겨서 return값을 받음.

     ![캡처](https://user-images.githubusercontent.com/62634760/106283600-7d762800-6285-11eb-9419-4b60cd9bf567.PNG)

    - 구현클래스 ItemApiController 작성
    - @RestController 애노테이션 설정으로 controller 클래스라고 설정
    - @RequestMapping을 통해 Http 주소 맵핑   
    - lombok인 ReqiredArgsConstructor를 통해 final로 된 변수에 의존관계 자동주입 
    - CrudController<Req, Res, Entity>를 상속받음
    - CrudController를 상속했기때문에 BaseService<Req, Res, Entity> 의존관계 자동 주입

      ![캡처](https://user-images.githubusercontent.com/62634760/106284579-bd89da80-6286-11eb-92e6-6e7397adc83d.PNG)
    
    4) service : Header로 만들어진 response return 
     - 추상클래스인 BaseService<Req, Res, Entity> 작성
     - Crudinterface를 해당<Req, Res>제네릭 값으로 상속받음.
     - repository의 자동의존관계 주입을 위해 @Component 등록 
     - protected 타입을 통해 상속받은 클래스에서도 사용가능하게 만듬.
     - 들어온 Entity 제네릭 타입으로 JpaRepository<Entity, Long> baseRepository를 생성하여 구현클래스에서 따로 만들 필요 없음.
     
       ![캡처](https://user-images.githubusercontent.com/62634760/106286171-b237ae80-6288-11eb-936d-366ca2acf817.PNG)

     - 구현클래스인 ItemApiLogicService 작성
     - @Service 애노테이션 등록
     - BaseService<ItemApiRequest, ItemApiResponse, Item>을 상속받음.
     - partnerRepository가 필요하기 때문에 @RequiredArgsConstructor를 통해 final로 의존관계주입 

       ![캡처](https://user-images.githubusercontent.com/62634760/106287574-49e9cc80-628a-11eb-9c15-e5ef2a19bbef.PNG)
     
     - 들어온 Header<ItemApiRequest>의 request 내용에 있는 data부분을 getData로 가져오고 Item.builder를 통해 객체 생성
     - itemRepository에 save를 통해 데이터 베이스에 sql 쿼리를 날려 데이터 저장 
     - reponse라는 이름의 메서드를 통해 Header부분을 포함하여 만들어진 Header<ItemApiResponse>를 return 받음.
     - response(newItem)을 controller로 retrun 
      
       ![캡처](https://user-images.githubusercontent.com/62634760/106288286-2e32f600-628b-11eb-8ccd-4d504ef8e0ec.PNG)

     - 매개변수로 들어온id를 baseRepository.findById를 통해 해당하는 Optional<item>을 가져옴
     - Optional기능인 .map을 통해 반환값을 Header<ItemApiResponse>로 바꾸고, 해당하는 값이 없을 경우 orElseGet을 통해 Header.ERROR를 띄어준        다.
    
       ![캡처](https://user-images.githubusercontent.com/62634760/106288780-d1840b00-628b-11eb-9fa4-68e9a38eabb0.PNG)

     - 매개변수로 들어온 update request의 getData를 통해 값을 가져오고 baseRepository에서 원하는 객체를 아이디를 통해 가져온다.
     - 해당하는 객체(Optional 형태)가 있으면 .map을 통해 들어온 request값으로 세팅해주고 baseRepository.save를 통해 저장한다.
     - 저장한 객체를 response메서드로 넘겨 원하는 Header<ItemApiResponse>를 return 한다.

       ![캡처1](https://user-images.githubusercontent.com/62634760/106288873-e95b8f00-628b-11eb-8130-7f88caa97ed7.PNG)

     - delete는 반환값은 response가 없으므로 그냥 Header만 반환
     - 들어온 request의 id값을 통해 baseRepository.findById(id)로 원하는 객체를 가져오고 baseRepository.findById.delete를 통해 그 객체를 지워준다. 그리고 Header만 반환하는 클래스 메서드인 Header.OK를 return한다.
     - orElseGet을 통해 해당하는 아이디의 객체가 없으면 데이터 없음의 Header.ERROR를 반환한다.

     - response 메서드는 return 값으로 ItemApiResponse를 반환하며, 매개변수로는 itme 객체를 받는다.
     - 새로운 ItemApiResponse.build를 통해 새로 생성됬거나 업데이트된 item 객체의 값을 저장한다.
     - Header의 클래스 메서드인 Header.OK(body)에 생성된 ItemApiResponse를 넘겨준다. 그럼 Header<ItemApiResponse>를 return 해준다.
     
       ![캡처2](https://user-images.githubusercontent.com/62634760/106288880-eb255280-628b-11eb-8aa9-5bca9497136f.PNG)


-----------------------------------------------------------------------------------------------------------------------------


*** Chrome의 YREC을 통해 간편하게 JSON 데이터 주고 받아 DB(Mysql)에 저장하기

1. post(create)

  ![post1](https://user-images.githubusercontent.com/62634760/106291694-4c025a00-628f-11eb-9d29-810a7c56dcf6.PNG)
  ![post2](https://user-images.githubusercontent.com/62634760/106291701-4dcc1d80-628f-11eb-9826-01f799e8a295.PNG)

2. get(read)

  ![get1](https://user-images.githubusercontent.com/62634760/106291707-502e7780-628f-11eb-93d0-443027b75583.PNG)
  ![get2](https://user-images.githubusercontent.com/62634760/106291715-515fa480-628f-11eb-955c-08a0bab2c2aa.PNG)

3. put(update)

  ![put1](https://user-images.githubusercontent.com/62634760/106291722-53296800-628f-11eb-9760-c2dc707a5d7e.PNG)
  ![put2](https://user-images.githubusercontent.com/62634760/106291726-54f32b80-628f-11eb-9e0d-a4dc229abdc9.PNG)

4. delete(delete)

  ![delete](https://user-images.githubusercontent.com/62634760/106291731-56bcef00-628f-11eb-8d86-c93cacfc524f.PNG)
  ![delete2](https://user-images.githubusercontent.com/62634760/106291733-57ee1c00-628f-11eb-87b6-7f11e893c6d7.PNG)




*** DB(Mysql)에 테스트 데이터 저장

  ![캡처](https://user-images.githubusercontent.com/62634760/106292193-e2cf1680-628f-11eb-9e01-6c3d1331d165.PNG)



*** 페이징 처리 1
 - front 단에서 버튼 클릭으로 원하는 유저 데이터 수를 페이지별로 보여줄 수 있는 페이징 처리
 - user에 관해서만 진행해보았다.
 - model 폴더 -> Pagination 클래스 생성
 - Pagination 클래스는 데이터베이스 페이지 정보를 가져옴.
    - private Integer totalPages; //총 몇개의 페이지가 있는지?
    - private Long totalElements; //총 몇개의 엘리먼트를 가지고 있는지? (총 유저 수가 몇명인지?)
    - private Integer currentPage; //현재 페이지는 몇번째인지?
    - private Integer currentElements; //총 몇개의 엘리먼트가 내려갔는지?

   ![캡처2](https://user-images.githubusercontent.com/62634760/106352460-a7cbf200-6326-11eb-900c-08c1a6e5ad2e.PNG)



 - Header클래스에 private Pagination pagination 변수 생성과 pagination데이터를 넣을 수 있는 클래스메서드를 만듬.(화면단에 데이터를 전송하기 위해서)
 
   ![캡처3](https://user-images.githubusercontent.com/62634760/106352468-b7e3d180-6326-11eb-8e03-4442c14791af.PNG)


 - Pageable 개념정리
   - 웹 개발 시 Pagination 과 Sorting은 필수적이라 할 수 있다. 하지만 개별적으로 구현하는 것과 페이징 쿼리를 날리는 것은 번거롭다. 
   - Spring Data JPA의 Pageable을 사용하면 이런 문제들을 쉽게 해결할 수 있고 서비스 로직에 집중할 수 있다.
   - Spring Data JPA 에서는, 페이징을 위해서 기본적으로 Page 라는 객체와, Pageable 이라는 객체를 가지고 있다
   - Pagable 이라는 객체는, 페이징 하는 방법을 기술해놓은 클래스(인터페이스) 라고 보면되고, Page 객체는 실제로 페이징으로 잘려진 객체들을 담고있는 객체라고 생각하면 된다.
   - Pageable 장점 
     - 요건에 맞는 Pagination을 구현할 수 있다.
     - 정렬이 필요한 데이터를 쉽게 Sorting 할 수 있다.
 - Service로직을 보면 반환 타입은 Header<List<UserApiResponse>>이고, 매개변수로 Pageable를 받고 있다.
 - userRepository모든 데이터를 pageable를 통해 찾아오고 Page<User>객체에 담는다.
 - List<UserApiResponse>가 필요함으로 stream.map을 사용하여 user객체를 response2(userApiResponse를 return)를 통해 userApiResponse로 만듬
 - stream.collect(Collectors.toList())로 List<userApiResponse>를 만듬.
 - 페이지 정보를 표시해주기 위해 Pagination 객체를 생성하여 데이터를 builder함.
    - page 객체는 각 user의 페이지 정보들을 포함하여 가져올 수 있음.(users는 page 객체)
       - users.getTotalPages()       : 전체 페이지 정보
       - users.getTotalElements()    : 전체 user수 정보
       - users.getNumber()           : 현재 페이지 정보
       - users.getNumberOfElements() : 현재 가져온 user정보 갯수
 - controller로 return할 Header.OK(userApiResponsesList, pagination) 생성

   ![캡처](https://user-images.githubusercontent.com/62634760/106352473-c8944780-6326-11eb-9125-895569e59d94.PNG)
 

 - getMapping으로 매개변수를 @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 15) Pageable pageable로 받음   
    - sort는 id를 기준으로 정렬 
    - direction : Sort.Direction.ASC(오름차순), Sort.Direction.DESC(내림차순)
    - size는 가져올 user정보 객체 수
    - 앞 설정을 가진 Pageable객체를 매개변수로 받음
 
   ![캡처1](https://user-images.githubusercontent.com/62634760/106352480-d1851900-6326-11eb-9c0c-cc603b143294.PNG)

 - 결과 
    - 첫번째 페이지 http://localhost:8080/api/user?page=0 (첫페이지는 생략가능) 
       ![1](https://user-images.githubusercontent.com/62634760/106352668-4ad13b80-6328-11eb-8343-6c13e2f13f2d.PNG)
       ![2](https://user-images.githubusercontent.com/62634760/106352671-4e64c280-6328-11eb-8af0-0f73d3185e05.PNG)

    - 두번째 페이지 http://localhost:8080/api/user?page=1 
       ![3](https://user-images.githubusercontent.com/62634760/106352673-50c71c80-6328-11eb-89b6-354ac5b9b6f9.PNG)
       ![4](https://user-images.githubusercontent.com/62634760/106352674-51f84980-6328-11eb-851a-a41a13ada3a3.PNG)

 - 미리 만들어둔 프론트 엔드단과 결합
   - http://localhost:8080/pages
     ![캡처](https://user-images.githubusercontent.com/62634760/106352737-e1056180-6328-11eb-8d95-415f98204397.PNG)

   - http://localhost:8080/pages/user (고객관리 란)
     ![캡처1](https://user-images.githubusercontent.com/62634760/106352738-e2368e80-6328-11eb-8d26-14a380e370e6.PNG)





*** 페이징 처리2
 - user 정보를 통해 orderGroup데이터와 item 목록까지 한꺼번에 json데이터로 받기 
 
 - UserOrderInfoApiResponse : 사용자의 주문 정보를 조회하는 API Response를 만듬

   ![1](https://user-images.githubusercontent.com/62634760/106372888-b0b8d400-63b7-11eb-9848-2845cb7f5f79.PNG)

 - UserApiResponse에 private List<OrderGroupApiResponse> orderGroupApiResponsesList; 추가

   ![2](https://user-images.githubusercontent.com/62634760/106372891-b1ea0100-63b7-11eb-83cf-995a3415b0e8.PNG)

 - OrderGroupApiResponse에 private List<ItemApiResponse> itemApiResponseList; 추가

   ![3](https://user-images.githubusercontent.com/62634760/106372892-b2829780-63b7-11eb-9ab4-0c32bb0fd776.PNG)

 - UserApiLogicService에 반환타입이 Header<UserOrderInfoApiResponse>인 orderInfo 메서드 생성
 - 매개변수인 id로 원하는 유저 정보를 userRepository 통해 가져옴
 - response2(user)를 통해 UserApiResponse 객체 생성
 - UserApiResponse안에 들어갈 orderGroupList 생성을 위해 가져온 user 객체에서 OrderGroupList()를 가져옴
 - 가져온 OrderGroupList()를 List<OrderGroupResponse>로 만들기 위해 Stream.map().collect(Collectors.toList()); 사용
 - 같은 방식으로 OrderGroupResponse에 들어갈 List<ItemApiResponse>를 만들기 위해 orderGroup.getOrderDetailList()에서 item을 가져와서 리스트를 만듬
 - 만들어진 List<ItemApiResponse>를 orderGroupApiResponse에 넣고, 만들어진 List<OrderGroupApiResponse>를 userApiResponse에 넣음
 - UserOrderInfoApiResponse를 생성하고 userApiResponse에를 build함.
 - Header.OK(userOrderInfoApiResponse)를 return 함.

   ![4](https://user-images.githubusercontent.com/62634760/106372893-b2829780-63b7-11eb-8933-cb09181abb6f.PNG)
   ![4-1](https://user-images.githubusercontent.com/62634760/106372894-b31b2e00-63b7-11eb-9edd-2e2e699272dc.PNG)

 - 컨트롤러 단에서 서비스 로직을 리턴함.
   ![5](https://user-images.githubusercontent.com/62634760/106372895-b31b2e00-63b7-11eb-97e9-2c03e3f79019.PNG)

