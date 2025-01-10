package tacocloud.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import tacocloud.domain.CustomUserDetails;

public record UserInfo(Long id, String username, Collection<? extends GrantedAuthority> authorities) {
    public static UserInfo of(CustomUserDetails customUser) {
        return new UserInfo(customUser.getId(), customUser.getUsername(), customUser.getAuthorities());
    }
}
