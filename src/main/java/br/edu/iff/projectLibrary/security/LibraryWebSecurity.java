package br.edu.iff.projectLibrary.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class LibraryWebSecurity extends WebSecurityConfigurerAdapter
{
    @Autowired
    private PersonDetailsService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().ignoringAntMatchers("/apirest/**")
            .and()
            .authorizeRequests()
                .antMatchers("/apirest/**").hasRole("ADMIN")
                .antMatchers("/librarians/mydata/**").hasAnyRole("ADMIN", "LIBRA")
                .antMatchers("/librarians").hasRole("ADMIN")
                .antMatchers("/librarians/**").hasRole("ADMIN")
                .antMatchers("/**").hasAnyRole("ADMIN", "LIBRA")
                .antMatchers("/libmembers/mydata/**").hasAnyRole("ADMIN", "LIBRA", "LIBMB")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .usernameParameter("email");
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }
    
}

