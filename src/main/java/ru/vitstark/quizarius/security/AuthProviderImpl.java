package ru.vitstark.quizarius.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.vitstark.quizarius.services.PersonDetailsService;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {
    private final PersonDetailsService personDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthProviderImpl(PersonDetailsService personDetailsService, PasswordEncoder passwordEncoder) {
        this.personDetailsService = personDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails personDetails = personDetailsService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, personDetails.getPassword())) {
            throw new BadCredentialsException("Неправильный пароль");
        }

        return new UsernamePasswordAuthenticationToken(personDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
