package config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@WebAppConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"mapper", "servlet", "utils", "dao", "filters"})
@PropertySource("classpath:application.properties")
public class MvcConfig {

}
