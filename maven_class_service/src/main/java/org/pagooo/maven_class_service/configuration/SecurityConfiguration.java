package org.pagooo.maven_class_service.configuration;


import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> {
            csrf.disable();
        })
        .authorizeHttpRequests((request) -> request
                        .requestMatchers("/","/member/**").permitAll()
                        .anyRequest().authenticated()

                )
        .formLogin(formLogin -> formLogin.loginPage("/member/login").permitAll()
                .defaultSuccessUrl("/")
                .failureHandler(authenticationFailureHandler)
                .permitAll()

        )
        .logout(logout -> logout.logoutUrl("/member/logout") // 기본 로그아웃 URL 설정 (POST 요청만 처리)
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) //모든 HTTP 메서드에서 로그아웃 요청 처리
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")

        );


        return http.build();
    }
@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);

        return authenticationManagerBuilder.build();
    }

}
