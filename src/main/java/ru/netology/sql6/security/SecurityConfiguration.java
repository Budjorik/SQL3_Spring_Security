package ru.netology.sql6.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Administrator").password("{noop}canAll").authorities("read", "write", "delete")
                .and()
                .withUser("Writer").password("{noop}canWrite").authorities("read", "write")
                .and()
                .withUser("Reader").password("{noop}canRead").authorities("read");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests().antMatchers("/persons/city/read/all", "/persons/person/read/all")
                .permitAll()
                .and()
                .authorizeRequests().antMatchers("/persons/person/delete").hasAuthority("delete")
                .and()
                .authorizeRequests().antMatchers("/persons/city/create", "/persons/person/create",
                        "/persons/person/update-phone").hasAuthority("write")
                .and()
                .authorizeRequests().antMatchers("/persons/city/read", "/persons/person/read/by-city",
                        "/persons/person/read/by-age/less", "/persons/person/read/by-name-and-surname")
                .hasAuthority("read");
    }
}
