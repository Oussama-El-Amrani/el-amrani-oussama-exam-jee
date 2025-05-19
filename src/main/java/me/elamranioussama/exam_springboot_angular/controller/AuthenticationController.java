package me.elamranioussama.exam_springboot_angular.controller;

import lombok.RequiredArgsConstructor;
import me.elamranioussama.exam_springboot_angular.dto.AuthenticationRequest;
import me.elamranioussama.exam_springboot_angular.dto.AuthenticationResponse;
import me.elamranioussama.exam_springboot_angular.dto.RegisterRequest;
import me.elamranioussama.exam_springboot_angular.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
