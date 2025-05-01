📁 Hello Spring - 요점 정리



01. 프로젝트 환경설정

프로젝트 생성

spring.io

라이브러리 살펴보기

View 환경 설정

빌드하고 실행하기

🔧 빌드 및 실행 방법

✅ Mac

./gradlew build

cd build/libs

java -jar hello-spring-0.0.1-SNAPSHOT.jar

✅ Windows

gradlew.bat build

cd build\libs

java -jar hello-spring-0.0.1-SNAPSHOT.jar

📌 위 명령어는 프로젝트를 빌드한 후 실행 파일(JAR)을 실행하는 과정입니다.

02. 스프링 웹 개발 기초

정적 컨텐츠 / MVC / API

📝 정적 컨텐츠 (Static Content)

resources/static 폴더에 위치한 정적 파일(.html, .css, .js 등)은 컨트롤러 로직 없이도 URL로 바로 접근 가능.

예: /hello.html 요청 시, resources/static/hello.html 파일을 그대로 반환.

🧱 MVC와 템플릿 엔진

🧩 MVC 구조

Model: 데이터 정보를 담음

View: 사용자에게 보이는 화면 (HTML 등)

Controller: 비즈니스 로직 처리 및 Model과 View 연결

🖼️ 템플릿 엔진 (Thymeleaf 등)

Controller에서 전달한 데이터를 활용하여 View를 동적으로 생성

예: model.addAttribute("name", "spring") → ${name}으로 템플릿에서 사용 가능

🔄 API 방식

✔️ @ResponseBody란?

ViewResolver를 거치지 않고, HTTP 응답 Body에 직접 데이터를 작성

텍스트 또는 JSON 형식 응답에 사용

⚙️ 동작 원리

@ResponseBody 사용 시 내부적으로 HttpMessageConverter가 작동

요청한 데이터 형식에 따라 적절한 Converter를 사용해 응답을 처리함

데이터 타입

사용되는 Converter

문자열 (String)

StringHttpMessageConverter

객체 (Object)

MappingJackson2HttpMessageConverter → JSON 변환

기타

기타 다양한 HttpMessageConverter가 자동 등록됨

🧠 참고: JSON 응답은 주로 @RestController나 @ResponseBody + 객체 반환 조합에서 사용됩니다.

03. 회원 관리 예제 - 백엔드 개발

비지니스 요구사항 정리

-데이터: 회원ID, 이름

-기능: 회원 등록, 조회

-아직 데이터 저장소가 선정되지 않음(가상의 시나리오)

📌 일반적인 웹 애플리케이션 계층 구조

![Image](https://github.com/user-attachments/assets/c796075b-1cd7-4695-b0de-17be60ff5046)

컨트롤러: 웹 MVC의 컨트롤러 역할

서비스: 핵심 비즈니스 로직 구현

리포지토리: 데이터베이스 접근 및 도메인 객체 저장/조회

도메인: 비즈니스 도메인 객체 (예: 회원, 주문 등)

🔄 클래스 간 의존 관계

![Image](https://github.com/user-attachments/assets/162dbb4e-d7ca-49ce-a833-926ba1d8ba00)

아직 데이터 저장소가 확정되지 않아 인터페이스 기반 설계로 유연하게 구성

향후 DB(RDB, NoSQL 등)로 전환하기 쉽도록 설계됨

개발 초기에는 메모리 기반 저장소(MemoryMemberRepository)를 사용

회원 도메인과 리포지토리 만들기

1. 회원 객체 만들기
2. 회원 리포지토리 인터페이스 만들기
3. 회원 리포지토리 메모리 구현체

회원 리포지토리 테스트 케이스 작성

 @AfterEach
한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있다. 이렇게
되면 다음 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있다.

 @AfterEach 를 사용하면 각 테스트가 종료될 때 마다 이 기능을 실행한다.
 여기서는 메모리 DB에 저장된 데이터를 삭제한다.

회원 서비스 개발

회원 서비스 테스트
기존에는 회원 서비스가 메모리 회원 리포지토리를 직접 생성하게 했다
회원 서비스 코드를 DI 가능하게 변경한다

회원 서비스 테스트
@BeforeEach : 각 테스트 실행 전에 호출된다. 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하
고, 의존관계도 새로 맺어준다