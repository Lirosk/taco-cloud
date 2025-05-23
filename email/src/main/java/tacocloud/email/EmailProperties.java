package tacocloud.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "tacocloud.email")
public class EmailProperties {
    private String username;
    private String password;
    private String host;
    private String mailbox;
    private long pollRate = 3000;

    public String getImapUrl() {
        return String.format("imaps://%s:%s@%s.%s", this.username, this.password, this.host, this.mailbox);
    }
}
