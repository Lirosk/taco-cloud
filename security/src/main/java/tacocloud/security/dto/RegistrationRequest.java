package tacocloud.security.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import tacocloud.domain.CustomUserDetails;

public record RegistrationRequest(String username, String password, String fullname, String street, String city,
                                  String state, String zip, String phone) {
    public CustomUserDetails toUser(PasswordEncoder passwordEncoder) {
        return new CustomUserDetails(username, passwordEncoder.encode(password), fullname, street, city, state, zip, phone);
    }
}
