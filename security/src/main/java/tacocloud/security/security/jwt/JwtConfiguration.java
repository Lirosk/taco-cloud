package tacocloud.security.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("security.jwt")
public class JwtConfiguration {
    private String secretKey;
    private int tokenExpireInMinutes;
}
