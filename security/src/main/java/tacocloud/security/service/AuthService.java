package tacocloud.security.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import tacocloud.data.UserRepository;
import tacocloud.domain.CustomUserDetails;
import tacocloud.security.dto.LoginRequest;
import tacocloud.security.dto.LoginResponse;
import tacocloud.security.dto.RegistrationRequest;
import tacocloud.security.dto.UserInfo;
import tacocloud.security.security.jwt.JwtEncoder;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public UserInfo attemptRegister(RegistrationRequest registrationRequest) {
        if (userRepository.findByUsername(registrationRequest.username()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        return UserInfo.of(userRepository.save(registrationRequest.toUser(passwordEncoder)));
    }

    public LoginResponse attemptLogin(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        String token = jwtEncoder.encode(user);
        return new LoginResponse(token);
    }
}
