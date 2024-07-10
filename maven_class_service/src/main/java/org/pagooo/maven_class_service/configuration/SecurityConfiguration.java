package org.pagooo.maven_class_service.configuration;


import lombok.RequiredArgsConstructor;
import org.pagooo.maven_class_service.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MemberService memberService;
    private final CustomAuthenticationProvider customAuthenticationProvider;



    @Bean
    UserAuthenticationFailureHandler getFailureHandler(){
        return new UserAuthenticationFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> {
            csrf.disable();
        });

        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/","/member/register","/member/email-auth").permitAll()
                        .anyRequest().authenticated()

                ).formLogin(withDefaults());

        http.formLogin(formLogin -> formLogin.loginPage("/member/login").failureHandler(getFailureHandler()).permitAll()
        );


        return http.build();
    }
@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider).userDetailsService(memberService)
                                        .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }

}
