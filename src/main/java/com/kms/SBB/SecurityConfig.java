package com.kms.SBB;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.AuthorizeHttpRequestsDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean //암호화를 진행할 메소드
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
                );


        // csrf라는 걸 security가 자동으로 해주는데 이러면 로그인할때 csrf 토큰도 같이 보내줘야되는데 지금은 사용안하는 잠시 비활성
        httpSecurity.csrf(auth -> auth.disable());

        httpSecurity
                .formLogin((auth) -> auth
                        .loginPage("/user/login")
                        //성공새 해당 경로로 리다이렉트
                        .defaultSuccessUrl("/"));
        httpSecurity
                .logout((logout) -> logout
                        // 로그아웃 요청을 처리할 경로를 지정
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .logoutSuccessUrl("/user/login")
                        //로그아웃 시 세션을 무효화 true일 시.
                        .invalidateHttpSession(true));

        return httpSecurity.build();
    }

    //AuthenticationManager는 스프링 시큐리티의 인증을 처리한다.
    //  사용자 인증 시 앞에서 작성한 UserSecurityService와 PasswordEncoder를 내부적으로 사용하여 인증과 권한 부여 프로세스를 처리한다.
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
