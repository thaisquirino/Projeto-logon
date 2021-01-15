package br.com.iblue.logon.controller;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}password").roles("USER")
                .and()
                .withUser("admin").
                password("{noop}password").roles("USER", "ADMIN");

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       //basic Autentication
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/findall/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/usuario").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api//usuario/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/usuario/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/usuario/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }



}


