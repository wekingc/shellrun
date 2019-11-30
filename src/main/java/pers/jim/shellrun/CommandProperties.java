package pers.jim.shellrun;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "command")
public class CommandProperties {
    @Getter
    @Setter
    private String os;

    @Getter
    @Setter
    private String line;
}
