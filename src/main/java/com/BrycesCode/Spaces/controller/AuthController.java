package com.BrycesCode.Spaces.controller;


import com.BrycesCode.Spaces.dto.RegisterRequest;
import com.BrycesCode.Spaces.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService _authService;

    public AuthController(AuthService authService) {
        _authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        _authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful",
                OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        _authService.verifyAccount(token);
        return new ResponseEntity<>("User Activated Successful",
                OK);
    }
}
