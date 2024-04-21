package com.BrycesCode.Spaces.service;

import com.BrycesCode.Spaces.dto.RegisterRequest;
import com.BrycesCode.Spaces.model.NotificationEmail;
import com.BrycesCode.Spaces.model.User;
import com.BrycesCode.Spaces.model.VerificationToken;
import com.BrycesCode.Spaces.repository.UserRepository;
import com.BrycesCode.Spaces.repository.VerificationTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
public class AuthService {

    private final PasswordEncoder _passwordEncoder;

    private final UserRepository _userRepository;

    private final VerificationTokenRepository _verificationTokenRepository;
    private final MailService _mailService;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, VerificationTokenRepository verificationTokenRepository, MailService mailService) {
        _passwordEncoder = passwordEncoder;
        _userRepository = userRepository;
        _verificationTokenRepository = verificationTokenRepository;
        _mailService = mailService;
    }
    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername().toLowerCase());
        user.setEmail(registerRequest.getEmail().toLowerCase());
        user.setPassword(_passwordEncoder.encode((registerRequest.getPassword())));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        _userRepository.save(user);

        String token = generateVerificationToken(user);
        _mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up for Spaces!," +
                "please click the link below to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));


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
