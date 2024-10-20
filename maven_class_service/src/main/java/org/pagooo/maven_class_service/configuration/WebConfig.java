package org.pagooo.maven_class_service.configuration;


import lombok.RequiredArgsConstructor;
import org.pagooo.maven_class_service.member.service.MemberService;
import org.pagooo.maven_class_service.member.service.impl.MemberServiceImpl;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@RequiredArgsConstructor
public class WebConfig {
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new UserAuthenticationFailureHandler();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
