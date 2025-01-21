package tacocloud.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tacocloud.security.dto.LoginRequest;
import tacocloud.security.dto.LoginResponse;
import tacocloud.security.dto.RegistrationRequest;
import tacocloud.security.dto.UserInfo;
import tacocloud.security.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfo register(@RequestBody @Validated RegistrationRequest registrationRequest) {
        return authService.attemptRegister(registrationRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest) {
        return authService.attemptLogin(loginRequest);
    }
}
