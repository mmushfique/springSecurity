package com.mush.springSecurity.security;

import com.sun.deploy.cache.InMemoryLocalApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    @Autowired
//    DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
// method 1 - > In memmory
//        authBuilder.inMemoryAuthentication()
//                .withUser("mush")
//                .password("mush")
//                .roles("USER")
//                .and()
//                .withUser("mush2")
//                .password("mush2")
//                .roles("ADMIN");

//        method 2 - > get from db using jdbc
//        authBuilder.jdbcAuthentication()
//                .dataSource(dataSource);

            /*when using jdbc directly and if needed to customize the user, authority table, then can chain and add the query
            * next to the .datasource method, using JPA can be different In providing a db for user storage(other than h2), can provide the
            * url and other props of db in property file*/

              /*
              way of inserting value to DB trough code (may be h2 db)
              * this also use work by storing the user in db, but inserting it here, can use sql too
              .withDefaultSchema()
                .withUser(
                        User.withUsername("mush")
                                .password("mush")
                                .roles("USER")
                )
                .withUser(User
                        .withUsername("admin")
                        .password("admin")
                        .roles("ADMIN")
                );*/

//        method 3 - > using the userdetails service, this can allow any type of ORM to connect, here JPA
        authBuilder.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER","ADMIN")
                .antMatchers("/","static/css").permitAll()
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
