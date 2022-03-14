package com.example.apirestv1.security;

import com.example.apirestv1.model.services.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    //ATTRIBUTES
    private final MyBasicAuthenticationEntryPoint myEntryPoint;
    private final MyUserDetailsService myUserDetailsService;
    private final PasswordEncoder encoder;

    //TESTING METHOD
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().anyRequest();
//    }

    //METHODS
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .httpBasic()
                .authenticationEntryPoint(myEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/me/**").hasRole("ADMIN") //FORBIDDEN TEST
                .antMatchers(HttpMethod.GET, "/users/**", "/series/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/users/**", "/series/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/series/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/series/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/series/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated();
                //.and()
                //.csrf().disable();
    }
}
