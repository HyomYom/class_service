package org.pagooo.maven_class_service.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Qualifier("memberServiceImpl")
    private final UserDetailsService userDetailsService;

//    public CustomAuthenticationProvider(UserDetailsService detailsService){
//        this.userDetailsService = detailsService;
//    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        String encPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        UserDetails user = userDetailsService.loadUserByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if(!BCrypt.checkpw(password, user.getPassword())){
            throw  new AuthenticationException("Invalid credential") {
            };
        }


        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
