package com.pizza_market.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;

    public WebSecurityConfig(){
        dataSource = new DriverManagerDataSource(System.getenv("DATABASE_URL"),
                System.getenv("DATABASE_USER"),
                System.getenv("DATABASE_PASSWORD"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/", "/index", "/pizza", "/signup").permitAll()
                    .antMatchers("/order", "/user").authenticated()
                    .antMatchers("/addPizza", "/admin", "/removePizza").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select email, password, is_active from client where email=?")
                .authoritiesByUsernameQuery(
                        "select c.email, ur.roles from client c join client_role ur on c.id = ur.id where c.email=?"
                );
    }
}
