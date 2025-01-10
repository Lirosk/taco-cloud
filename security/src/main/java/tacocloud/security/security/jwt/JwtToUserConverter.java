package tacocloud.security.security.jwt;

import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.RequiredArgsConstructor;
import tacocloud.data.UserRepository;
import tacocloud.domain.CustomUserDetails;

@Component
@RequiredArgsConstructor
public class JwtToUserConverter {
    private final UserRepository userRepository;

    public CustomUserDetails convert(DecodedJWT decodedJWT) {
        Long id = Long.valueOf(decodedJWT.getSubject());
        String username = String.valueOf(decodedJWT.getClaim("username").asString());
        return userRepository.findByIdAndUsername(id, username);
    }
}
