package com.banking.springboot.auth;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider implements AuthenticationProvider {

    private final AttemptRepository attemptRepository;

    public AuthProvider(AttemptRepository attemptRepository) {
        this.attemptRepository = attemptRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        Optional<Attempt> userAttempts = attemptRepository.findAttemptsByUsername(username);
        if (userAttempts.isPresent()) {
            Attempt attempts = userAttempts.get();
            attempts.setAttempts(attempts.getAttempts() + 1);
            attemptRepository.save(attempts);
        } else {
            Attempt attempts = new Attempt();
            attempts.setUsername(username);
            attempts.setAttempts(1);
            attemptRepository.save(attempts);
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}