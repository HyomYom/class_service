package org.pagooo.maven_class_service.configuration;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "";
        if(exception instanceof BadCredentialsException ){
            errorMessage = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해 주십시오.";
        } else if (exception instanceof InternalAuthenticationServiceException){
            errorMessage = exception.getMessage();
        }
        else if(exception instanceof DisabledException){
            errorMessage = "계정이 비활성화 되었습니다. 관리자에게 문의하세요";
        }
        else if (exception instanceof CredentialsExpiredException){
            errorMessage = "비밀번호 유효기간이 만료 되었습니다. 관리자에게 문의하세요.";
        }
        else if(exception instanceof UsernameNotFoundException){
            errorMessage = exception.getMessage();
        }
        else {
            errorMessage = "알수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요.";
        }
        log.error("UserAuthenticationFailureHandler : " + errorMessage);
        String encodedErrorMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        setDefaultFailureUrl("/member/login?error=true&errorMessage="+encodedErrorMessage); // 리다이렉트할 URL 설정
        super.onAuthenticationFailure(request, response, exception);
    }
}
