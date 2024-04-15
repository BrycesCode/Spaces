package com.BrycesCode.Spaces.service;

import com.BrycesCode.Spaces.dto.RegisterRequest;
import com.BrycesCode.Spaces.model.User;
import com.BrycesCode.Spaces.model.VerificationToken;
import com.BrycesCode.Spaces.repository.UserRepository;
import com.BrycesCode.Spaces.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class AuthService {

    private PasswordEncoder _passwordEncoder;

    private UserRepository _userRepository;

    private VerificationTokenRepository _verificationTokenRepository;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, VerificationTokenRepository verificationTokenRepository) {
        _passwordEncoder = passwordEncoder;
        _userRepository = userRepository;
        _verificationTokenRepository = verificationTokenRepository;
    }

    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(_passwordEncoder.encode((registerRequest.getPassword())));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        _userRepository.save(user);
        String token = generateVerificationToken(user);
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        _verificationTokenRepository.save(verificationToken);
        return token;
    }
}
