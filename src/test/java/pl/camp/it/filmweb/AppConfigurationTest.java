package pl.camp.it.filmweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import pl.camp.it.filmweb.filter.UserFilter;
import pl.camp.it.filmweb.session.SessionObject;

@Configuration
@ComponentScan(basePackages = {"pl.camp.it.filmweb.controllers", "pl.camp.it.filmweb.services"})
public class AppConfigurationTest {

    @Bean
    @SessionScope
    public SessionObject sessionObject() {
        SessionObject sessionObject = new SessionObject();
        sessionObject.setUserFilter(new UserFilter());
        return sessionObject;
    }
}
