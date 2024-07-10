package org.pagooo.maven_class_service.configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "";
        if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException){
            errorMessage = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해 주십시오.";
        }
        else if(exception instanceof DisabledException){
            errorMessage = "계정이 비활성화 되었습니다. 관리자에게 문의하세요";
        }
        else if (exception instanceof CredentialsExpiredException){
            errorMessage = "비밀번호 유효기간이 만료 되었습니다. 관리자에게 문의하세요.";
        }
        else {
            errorMessage = "알수 없는 이유로 로긍니에 실패하였습니다. 관리자에게 문의하세요.";
        }


        setUseForward(true);
        setDefaultFailureUrl("/member/login?error=true");
        request.setAttribute("errorMessage",errorMessage);
        super.onAuthenticationFailure(request, response, exception);
    }
}
