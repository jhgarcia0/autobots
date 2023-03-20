package unit.caruru.autobots;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {
    @Bean(name="applicationName")
    public String applicationName() {
        return "CarCtrl";
    }
}
