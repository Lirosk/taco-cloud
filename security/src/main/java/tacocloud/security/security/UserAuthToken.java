package tacocloud.security.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import tacocloud.domain.CustomUserDetails;

public class UserAuthToken extends AbstractAuthenticationToken {
    private final CustomUserDetails user;

    public UserAuthToken(CustomUserDetails user) {
        super(user.getAuthorities());
        this.user = user;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public CustomUserDetails getPrincipal() {
        return user;
    }
}
