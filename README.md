# spring-study-eclipse

# 기능
- 데이터베이스 연결 (oracle or mysql      : default : mysql)
- 회원가입
- 로그인      
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  - jwt를 통해 권한 인증     
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  - 쿠키를 통해 토큰 값 전달          
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  - 로그인 했을 시 권한을 획득      : 권한(사용자, 메니저, 관리자) 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;        
- 게시판 (생성, 삭제, 수정)

# 기타
- 테스트 코드를 작성하여 각 기능 동작의 유무를 판단했습니다.      
- API 문서화를 위해 swagger 사용

# 발생한 에러
Bean에 해당 클래스가 존재하지 않습니다.
- 빈에 포함하게 하는 어노테이션을 추가했는가?
- 루트 파일인 ~Application.java 가 포함된 패키지를 포함하여 패키지를 생성해야 함  
     ex) com.example.jpaconnect => com.example.jpaconnect.model
