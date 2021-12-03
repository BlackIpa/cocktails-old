package com.cocktails.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/users","/account","/favourites",
                        "/custom","/create-cocktail","/save-cocktail").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
            	.loginPage("/login")
            	.usernameParameter("email")
                .defaultSuccessUrl("/recipes", true)
                .failureUrl("/login.html?error=true")
                .permitAll()
                .and()
                .logout().deleteCookies("JSESSIONID")
                .and()
                .rememberMe().key("uniqueAndSecret").tokenValiditySeconds(60)
		        .and()
				.exceptionHandling().accessDeniedPage("/access-denied");
    }
}




















