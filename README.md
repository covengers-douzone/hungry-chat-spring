## hungry-chat-spring

### spring boot API & JWT 


## Spring
JSON Web Token 발행

① 로그인 URL로 접속 시, CustomAuthenticationFilter의 attemptAuthentication() Method가 호출 됨

② HttpRequest에서 username, password 정보 객체화

③ 로그인 정보로 AuthenticationManager에서 사용할 수 있는 UsernamePasswordAuthenticationToken 생성

④ AuthenticationManager의 authenticate() Method 호출

⑤ AuthenticationManager가 내부적으로 DaoAuthenticationProvider 할당

⑥ DaoAuthenticationProvider의 retrieveUser() Method에서 UserDetailsService 구현체 호출

⑦ UserDetailsService의 loadUserByUsername() Method가 실제 DB에서 사용자 정보 조회하는 로직

⑧ loadUserByUsername() Method에서 조회된 사용자 정보로 User 객체 생성 및 반환

⑨ DaoAuthenticationProvider의 additionalAuthenticationChecks() Method에서 패스워드 비교

⑩ AbstractUserDetailsAuthenticationProvider의 authenticate() Method에서 createSuccessAuthentication() Method 호출

⑪ CustomAuthenticationFilter의 successfulAuthentication() Method에서 JWT 발행

----
## Front


1. 생성된 JWT 토큰을 헤더에 넣어 Client로 반환 & DB 저장
2. Client는 받은 Token을 localStorage에 저장
3. 다음 요청시 localStorage에 저장된 토큰 정보를 헤더에 포함하여 요청
4. (chat 부분 요청은 Node 서버에서 처리) Node 서버에서 받은 토큰을 Decoding
5. DB로부터 토큰을 조회하고 받은 토큰과 비교 및 검증
6. 토큰값이 일치하다면 인증 처리, 일치하지 않을 경우 로그인 페이지로 리다이렉션
7. 토큰의 만료 시간을 조회, 만료 시간에 도달했다면 마찬가지로 로그인 페이지로 리다이렉션 

