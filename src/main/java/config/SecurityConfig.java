package config;

import filters.ApiAccessDeniedHandler;
import filters.ApiEntryPoint;
import filters.ApiLogoutSuccessHandler;
import filters.JwtAuthenticationFilter;
import filters.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
    private DataSource dataSource = context.getBean(DataSource.class);

    @Value("${jwt.signing.key}")
    private String jwtKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.exceptionHandling()
                .authenticationEntryPoint(new ApiEntryPoint());
        http.exceptionHandling()
                .accessDeniedHandler(new ApiAccessDeniedHandler());

        http.logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(new ApiLogoutSuccessHandler());

        http.authorizeRequests()
                .antMatchers("/api/login/**").permitAll()
                .antMatchers("/api/version/**").permitAll()
                .antMatchers("/api/orders/**").authenticated()
                .antMatchers("/api/users/**").authenticated();



        var loginFilter = new JwtAuthenticationFilter(authenticationManager(), "/api/login", jwtKey);

        http.addFilterAfter(loginFilter, LogoutFilter.class);

        var jwtAuthFilter = new JwtAuthorizationFilter(authenticationManager(), jwtKey);
        http.addFilterBefore(jwtAuthFilter, LogoutFilter.class);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {

        builder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
